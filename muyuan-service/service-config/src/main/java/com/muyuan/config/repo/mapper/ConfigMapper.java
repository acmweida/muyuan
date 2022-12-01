package com.muyuan.config.repo.mapper;

import com.muyuan.common.mybatis.jdbc.ConfigBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.config.repo.dataobject.ConfigDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 参数配置Mapper接口
 *
 * @author wd
 * @date 2022-11-30T10:41:23.089+08:00
 */
public interface ConfigMapper extends ConfigBaseMapper<ConfigDO> {


    String ID = "id" ;
    String NAME = "name" ;
    String CONFIG_KEY = "configKey" ;
    String CONFIG_VALUE = "configValue" ;
    String TYPE = "type" ;
    String CREATE_BY = "createBy" ;
    String CREATE_TIME = "createTime" ;
    String UPDATE_BY = "updateBy" ;
    String UPDATE_TIME = "updateTime" ;
    String REMARK = "remark" ;
    String CREATOR = "creator" ;
    String UPDATER = "updater" ;

    @Options(useGeneratedKeys = true, keyProperty = "id" )
    @InsertProvider(value = CrudSqlProvider.class, method = "insert" )
    Integer insertAuto(ConfigDO dataObject);

}
