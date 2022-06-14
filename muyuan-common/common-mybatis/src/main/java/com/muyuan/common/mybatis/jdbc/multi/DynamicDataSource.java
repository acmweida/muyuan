package com.muyuan.common.mybatis.jdbc.multi;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

    public void setMutiDateSourceConfig(MutiDataSourceConfig config) {
        DynamicDataSourceContextHolder.setMutiDataSourceConfig(config);
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        targetDataSources.forEach((k,v) -> {
            DynamicDataSourceContextHolder.getDataSourceIds().add(k);
        });
    }
}
