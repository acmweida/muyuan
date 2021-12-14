package com.muyuan.common.repo.jdbc.multi;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        targetDataSources.forEach((k,v) -> {
            DynamicDataSourceContextHolder.getDataSourceIds().add((String) k);
        });
    }
}
