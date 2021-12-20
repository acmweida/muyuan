package com.muyuan.common.repo.jdbc.multi;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotatedElementUtils;

@Slf4j
public class DynamicDataSourceInterceptor implements MethodInterceptor {


//    @Before("@annotation(dataSource)")
//    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
//        String dsId = dataSource.value();
//        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
//            log.info("数据源(" + dsId + ")不存在-" + point.getSignature());
//        } else {
//            log.info("使用数据源(" + dsId + ")-" + point.getSignature());
//
//            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
//        }
//    }
//
//    @After("@annotation(dataSource)")
//    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
//        log.info("恢复数据源-" + point.getSignature());
//
//        DynamicDataSourceContextHolder.clearDataSourceType();
//    }

    public void changeDataSource(DataSource dataSource) throws Throwable {
        String dsId = dataSource.value();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.info("数据源:{} 不存在-",dsId);
        } else {
            log.info("使用数据源:{}" , dsId);

            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }
    }

    public void restoreDataSource() {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        DataSource dataSource;
        if (AnnotatedElementUtils.hasAnnotation(methodInvocation.getMethod(),DataSource.class)) {
            dataSource = AnnotatedElementUtils.findMergedAnnotation(methodInvocation.getMethod(),DataSource.class);
        } else {
            dataSource = AnnotatedElementUtils.findMergedAnnotation(methodInvocation.getMethod().getDeclaringClass(),DataSource.class);
        }
        if (null != dataSource) {
            log.info("change datasource {} meth:{}", dataSource.value(), methodInvocation.getMethod().getName());
        } else {
            log.info("use default datasource");
        }
        changeDataSource(dataSource);
        Object ret =  methodInvocation.proceed();
        log.info("clear datasource {} meth:{}",dataSource.value(),methodInvocation.getMethod().getName());
        restoreDataSource();
        return ret;

    }
}
