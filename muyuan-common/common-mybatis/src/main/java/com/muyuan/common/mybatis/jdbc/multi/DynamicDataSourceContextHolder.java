package com.muyuan.common.mybatis.jdbc.multi;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();
    private static final List<Object> dataSourceIds = new ArrayList();

    private static MutiDataSourceConfig mutiDataSourceConfig;

    public static void setMutiDataSourceConfig(MutiDataSourceConfig mutiDataSourceConfig) {
        DynamicDataSourceContextHolder.mutiDataSourceConfig = mutiDataSourceConfig;
    }

    public static MutiDataSourceConfig getMutiDataSourceConfig() {
        return mutiDataSourceConfig;
    }

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static boolean isSplit() {
        return null != mutiDataSourceConfig;
    }

    public static String getDataSourceType() {
        return (String) contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    protected static List<Object> getDataSourceIds() {
        return dataSourceIds;
    }
}
