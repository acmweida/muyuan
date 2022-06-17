package com.muyuan.common.core.constant;

public interface GlobalConst {

    String MASTER_PREFIX = "MASTER_";
    String SLAVE_PREFIX = "SLAVE_";

    String TRUE ="0";

    String FALSE = "1";

    String ID = "id";

    long KB = 1024;

    long MB = KB << 10;

    long GB = MB << 10;

    Object NULL = null;

    String TOKEN = "token";

    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String HTTP = "http://";

    String HTTPS = "https://";

    String UTF8 = "UTF-8";

    /**
     * 菜单类型（目录）
     */
    String TYPE_DIR = "M";

    /**
     * 菜单类型（菜单）
     */
    String TYPE_MENU = "C";

    /**
     * 菜单类型（按钮）
     */
    String TYPE_BUTTON = "F";

    /**
     * 是否菜单外练（否）
     */
    String NO_FRAME = "1";

    /**
     * 是否菜单外练（是）
     */
    String YES_FRAME = "0";

    /**
     * Layout组件标识
     */
    String LAYOUT = "Layout";

    /**
     * InnerLink组件标识
     */
    String INNER_LINK = "InnerLink";

    /**
     * ParentView组件标识
     */
    String PARENT_VIEW = "ParentView";

    String UNIQUE = "0";

    String NOT_UNIQUE = "1";

    String DEFAULT_EMAIL_REGEX = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
}
