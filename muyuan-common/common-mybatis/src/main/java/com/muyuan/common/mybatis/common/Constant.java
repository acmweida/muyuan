package com.muyuan.common.mybatis.common;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    String UPDATE = "$update";

    String EXCLUDE_COLUMN = "$exclude_column";

    String UPDATE_PREFIX = "#UPDATE_";

    String GTE_PREFIX = "#GTE_";

    String GT_PREFIX = "#GT_";

    String LTE_PREFIX = "#LTE_";

    String LT_PREFIX = "#LE_";

    List<Class> JDBC_TYPE = Arrays.asList(int.class, Integer.class, String.class, Date.class, java.sql.Date.class,
            boolean.class, Boolean.class, Character.class, char.class, byte.class, Byte.class, short.class, Short.class,
            long.class, Long.class, float.class, Float.class, Double.class, double.class, byte[].class, Time.class,
            Timestamp.class, BigDecimal.class, Clob.class, Blob.class);

    String serialVersionUID = "serialVersionUID";

    String[] DEFAULT_EXCLUDE_COLUMN = new String[]{serialVersionUID};

}
