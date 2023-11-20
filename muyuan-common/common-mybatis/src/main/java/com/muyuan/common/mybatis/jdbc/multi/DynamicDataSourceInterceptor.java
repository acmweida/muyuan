package com.muyuan.common.mybatis.jdbc.multi;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.annotation.AnnotationUtils;

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

    public void changeDataSource(DataSource dataSource,String methodName) {
        String dsId = dataSource.value();
//        if (StringUtils.isEmpty(dsId)) {
//            DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceContextHolder.getMutiDataSourceConfig().getDefaultDateSource());
//        }
         boolean splitFlag = DynamicDataSourceContextHolder.isSplit();
        // 读写分离
        if (splitFlag && DynamicDataSourceContextHolder.getMutiDataSourceConfig().isReadWriteSplitDateSource(dsId)) {
            changeSourceSplit(dsId,methodName);
        } else {
            changeSource(dsId);
        }
    }

    public void changeSourceSplit(String dsId,String methodName) {
        MutiDataSourceConfig mutiDataSourceConfig = DynamicDataSourceContextHolder.getMutiDataSourceConfig();
        if ( !mutiDataSourceConfig.isReadWriteSplitDateSource(dsId) ) {
            log.error("数据源:{} 不存在-",dsId);
        }
        // 读写分离配置判断
        if (mutiDataSourceConfig.isReadMethod(methodName)) {
            dsId = mutiDataSourceConfig.getReadDateSourceId(dsId);
        } else {
            dsId = mutiDataSourceConfig.getWriteDateSourceId(dsId);
        }
        if (!dsId.equals(DynamicDataSourceContextHolder.getDataSourceType())) {
            log.info("使用数据源:{}", dsId);
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    public void changeSource(String dsId) {
        if ( !DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error("数据源:{} 不存在-",dsId);
        }
        if (!dsId.equals(DynamicDataSourceContextHolder.getDataSourceType())) {
            log.info("使用数据源:{}", dsId);
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    public void restoreDataSource() {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        DataSource dataSource = AnnotationUtils.findAnnotation(methodInvocation.getMethod(),DataSource.class);
        if (ObjectUtils.isEmpty(dataSource)) {
            dataSource = AnnotationUtils.findAnnotation(methodInvocation.getMethod().getDeclaringClass(), DataSource.class);
        }
        if (null != dataSource) {
            log.info("change datasource {} meth:{}", dataSource.value(), methodInvocation.getMethod().getName());
        } else {
            log.info("use default datasource");
        }
        changeDataSource(dataSource,methodInvocation.getMethod().getName());
        Object ret =  methodInvocation.proceed();
//        log.info("clear datasource {} meth:{}",dataSource.value(),methodInvocation.getMethod().getName());
//        restoreDataSource();
        return ret;

    }
}
