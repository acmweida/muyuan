package com.muyuan.config.repo.mapper;

import com.muyuan.common.mybatis.jdbc.ConfigBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.config.repo.dataobject.ConfigDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 参数配置Mapper接口
 *
 * @author ${author}
 * @date 2022-11-29T16:27:55.007+08:00
 */
public interface ConfigMapper extends ConfigBaseMapper<ConfigDO> {

    String CONFIG_ID = "configId";
    String CONFIG_NAME = "configName";
    String CONFIG_KEY = "configKey";
    String CONFIG_VALUE = "configValue";
    String CONFIG_TYPE = "configType";
    String CREATE_BY = "createBy";
    String CREATE_TIME = "createTime";
    String UPDATE_BY = "updateBy";
    String UPDATE_TIME = "updateTime";
    String REMARK = "remark";

    @Options(useGeneratedKeys = true, keyProperty = "configId")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(ConfigDO dataObject);

}
