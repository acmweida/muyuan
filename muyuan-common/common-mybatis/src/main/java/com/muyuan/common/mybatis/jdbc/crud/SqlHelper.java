package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.context.ApplicationContextHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @ClassName SqlHelper
 * Description TODO
 * @Author 2456910384
 * @Date 2022/4/29 16:28
 * @Version 1.0
 */
@Slf4j
public class SqlHelper {



    /**
     * 主要用于 service 和 ar
     */
    public static SqlSessionFactory FACTORY;

    /**
     * 获取SqlSessionFactory
     *
     * @return SqlSessionFactory
     * @since 3.3.0
     */
    public static SqlSessionFactory sqlSessionFactory() {
        return ApplicationContextHandler.getContext().getBean(SqlSessionFactory.class);
    }


    /**
     * 执行批量操作
     *
     * @param entityClass 实体
     * @param log         日志对象
     * @param consumer    consumer
     * @return 操作结果
     * @since 3.4.0
     */
    @SneakyThrows
    public static boolean executeBatch(Consumer<SqlSession> consumer)  {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
        SqlSessionHolder sqlSessionHolder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(sqlSessionFactory);
        boolean transaction = TransactionSynchronizationManager.isSynchronizationActive();
        if (sqlSessionHolder != null) {
            SqlSession sqlSession = sqlSessionHolder.getSqlSession();
            //原生无法支持执行器切换，当存在批量操作时，会嵌套两个session的，优先commit上一个session
            //按道理来说，这里的值应该一直为false。
            sqlSession.commit(!transaction);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        if (!transaction) {
            log.warn("SqlSession [" + sqlSession + "] Transaction not enabled");
        }
        try {
            consumer.accept(sqlSession);
            //非事物情况下，强制commit。
            sqlSession.commit(!transaction);
            return true;
        } catch (Throwable t) {
            sqlSession.rollback();
            Throwable unwrapped = ExceptionUtil.unwrapThrowable(t);
            if (unwrapped instanceof PersistenceException) {
                MyBatisExceptionTranslator myBatisExceptionTranslator
                        = new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true);
                Throwable throwable = myBatisExceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
                if (throwable != null) {
                    throw throwable;
                }
            }
            throw unwrapped;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 执行批量操作
     *
     * @param entityClass 实体类
     * @param log         日志对象
     * @param list        数据集合
     * @param batchSize   批次大小
     * @param consumer    consumer
     * @param <E>         T
     * @return 操作结果
     * @since 3.4.0
     */
    public static <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        Assert.isTrue(batchSize > 1, "batchSize must not be less than one");
        return !ObjectUtils.isEmpty(list) && executeBatch(sqlSession -> {
            int size = list.size();
            int i = 1;
            for (E element : list) {
                consumer.accept(sqlSession, element);
                if ((i % batchSize == 0) || i == size) {
                    sqlSession.flushStatements();
                }
                i++;
            }
        });
    }

//    /**
//     * 批量更新或保存
//     *
//     * @param entityClass 实体
//     * @param log         日志对象
//     * @param list        数据集合
//     * @param batchSize   批次大小
//     * @param predicate   predicate(新增条件) notNull
//     * @param consumer    consumer（更新处理） notNull
//     * @param <E>         E
//     * @return 操作结果
//     * @since 3.4.0
//     */
//    public static <E> boolean saveOrUpdateBatch(Class<?> entityClass, Class<?> mapper, Log log, Collection<E> list, int batchSize, BiPredicate<SqlSession,E> predicate, BiConsumer<SqlSession, E> consumer) {
//        String sqlStatement = getSqlStatement(mapper, SqlMethod.INSERT_ONE);
//        return executeBatch(entityClass, log, list, batchSize, (sqlSession, entity) -> {
//            if (predicate.test(sqlSession, entity)) {
//                sqlSession.insert(sqlStatement, entity);
//            } else {
//                consumer.accept(sqlSession, entity);
//            }
//        });
//    }


}
