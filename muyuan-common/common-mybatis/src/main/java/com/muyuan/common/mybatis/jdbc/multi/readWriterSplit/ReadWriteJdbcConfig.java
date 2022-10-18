package com.muyuan.common.mybatis.jdbc.multi.readWriterSplit;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @ClassName ReadWriteJdbcConfig
 * Description 读写分离配置
 * @Author 2456910384
 * @Date 2022/6/13 17:08
 * @Version 1.0
 */
@Data
public class ReadWriteJdbcConfig {

    @Value("${db.read-method-prefix:select,find,get}")
    protected List<String> readMethodPrefix;

    @Value("${db.write-method-prefix:insert,update,delete,del}")
    protected List<String> writeMethodPrefix;

    private String defaultDateSource;

    private List<JdbcConfig> masters;

    private List<JdbcConfig> slaves;
}
