package com.muyuan.common.mybatis.jdbc.crud;

public interface Constant {

    String PAGE_FIELD = "#page";

    String ORDERBY = "#ORDERBY";

    String[] ALL = new String[]{"*"};

    String CONDITION_PREFIX = "$";

    String TABLE_PREFIX = "t_";

    String SELECT = "select ";

    String FROM = " from ";

    String WHERE = " where ";

    String LIMIT = " limit ";

    String AND = " and ";

    String CONDITION = "$CONDITION";

    String COLUMN = "$column";

    String GTE_PREFIX = "#GTE_";

    String GT_PREFIX = "#GT_";

    String LTE_PREFIX = "#LTE_";

    String LT_PREFIX = "#LE_";

}
