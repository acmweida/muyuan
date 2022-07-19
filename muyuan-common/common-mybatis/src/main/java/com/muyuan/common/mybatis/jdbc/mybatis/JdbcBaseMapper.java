package com.muyuan.common.mybatis.jdbc.mybatis;


public interface JdbcBaseMapper<T>  {

    int DEFAULT_BATCH_SIZE = 100;

    String ID = "id";

    String STATUS = "status";

    String TYPE = "type";

    String UPDATER = "updater";

    String UPDATE_BY = "updateBy";

    String CREATOR = "creator";

    String CREATE_BY = "createBy";

    String CREATE_TIME = "createTime";

    String UPDATE_TIME = "updateTime";
}
