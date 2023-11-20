package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.CommonBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.OperLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志记录Mapper接口
 *
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 */
@Mapper
public interface OperLogMapper extends CommonBaseMapper<OperLogDO> {


}
