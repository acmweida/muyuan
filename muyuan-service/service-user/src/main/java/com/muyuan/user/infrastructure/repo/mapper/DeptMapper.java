package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.DeptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName MenuMapper
 * Description MenuMapper
 * @Author 2456910384
 * @Date 2022/10/13 14:17
 * @Version 1.0
 */
@Mapper
public interface DeptMapper extends UserBaseMapper<DeptDO> {


}
