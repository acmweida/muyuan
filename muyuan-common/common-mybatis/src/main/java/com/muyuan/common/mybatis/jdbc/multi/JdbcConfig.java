package com.muyuan.common.mybatis.jdbc.multi;

import lombok.Data;

@Data
public class JdbcConfig {

    private String username;

    private String password;

    private String url;

    private String driverClassName;
}
