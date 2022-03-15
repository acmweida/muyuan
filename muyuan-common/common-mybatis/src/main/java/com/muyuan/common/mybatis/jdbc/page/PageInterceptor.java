package com.muyuan.common.mybatis.jdbc.page;

import com.muyuan.common.mybatis.jdbc.crud.Constant;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@Intercepts(@Signature(type = StatementHandler.class,args = {Connection.class,Integer.class},method = "prepare"))
public class PageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object target =  invocation.getTarget();
        if (target instanceof RoutingStatementHandler) {
            RoutingStatementHandler  routingStatementHandler = (RoutingStatementHandler) target;
            BoundSql boundSql = routingStatementHandler.getBoundSql();

            Object parameterObject = boundSql.getParameterObject();
            Page page = findPage(parameterObject);
            if (null != page) {
                RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
                MetaObject metaObject=MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
                Executor executor=(Executor) metaObject.getValue("delegate.executor");
                String sql =  boundSql.getSql();
                // 总条数
                String countSql = "select count(1) from ( " + sql + " ) as temp";
                metaObject.setValue("delegate.boundSql.sql",countSql);
                PreparedStatement preparedStatement = (PreparedStatement) invocation.proceed();
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();
                Integer totalCount = resultSet.getInt(1);
                page.setTotal(totalCount);

                // 分页结果
                String limitSql = sql + Constant.LIMIT + page.getPageNum() * (page.getPageSize() -1) + "," + page.getPageSize();
                metaObject.setValue("delegate.boundSql.sql",limitSql);
                Object result = invocation.proceed();
                return result;
            }
        }

        return invocation.proceed();
    }

    /**
     * 分页参数
     *
     * @param args
     * @return
     */
    private Page findPage(Object args) {
        if (args instanceof Map) {
            for (Map.Entry entry : ((Map<?, ?>) args).entrySet()) {
                if (entry.getValue() instanceof Page) {
                    return (Page) entry.getValue();
                }
            }
        } else if (args instanceof Page) {
            return (Page) args;
        }
        return null;
    }

}
