package com.muyuan.config.repo.mapper;

import com.muyuan.common.mybatis.jdbc.CommonBaseMapper;
import com.muyuan.config.repo.dataobject.ConfigDO;
import org.apache.ibatis.annotations.Options;

/**
 * 参数配置Mapper接口
 *
 * @author wd
 * @date 2022-11-30T10:41:23.089+08:00
 */
public interface CommonMapper extends CommonBaseMapper<ConfigDO> {

    String ID = "id" ;
    String NAME = "name" ;
    String CONFIG_KEY = "configKey" ;
    String CONFIG_VALUE = "configValue" ;
    String TYPE = "type" ;
    String REMARK = "remark" ;


}
