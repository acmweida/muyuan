package com.muyuan.common.repo.jdbc.multi;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-10)
@Component
@Slf4j
public class DynamicDataSourceAspect {


    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String dsId = dataSource.value();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.info("数据源(" + dsId + ")不存在-" + point.getSignature());
        } else {
            log.info("使用数据源(" + dsId + ")-" + point.getSignature());

            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        log.info("恢复数据源-" + point.getSignature());

        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
