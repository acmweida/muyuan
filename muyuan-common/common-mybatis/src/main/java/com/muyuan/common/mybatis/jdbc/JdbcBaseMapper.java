package com.muyuan.common.mybatis.jdbc;


public interface JdbcBaseMapper<T>  {

    int DEFAULT_BATCH_SIZE = 100;

    String ID = "id";

    String NAME = "name";

    String STATUS = "status";

    String TYPE = "type";

    String UPDATER = "updater";

    String UPDATE_BY = "updateBy";

    String CREATOR = "creator";

    String CREATE_BY = "createBy";

    String CREATE_TIME = "createTime";

    String UPDATE_TIME = "updateTime";

    String ORDER_NUM = "orderNum";

    String PARENT_ID = "parentId";

    String CATEGORY_CODE = "categoryCode";

    String BRAND_ID = "brandId";

    String CODE = "code";

    String PLATFORM_TYPE = "platformType";
}
