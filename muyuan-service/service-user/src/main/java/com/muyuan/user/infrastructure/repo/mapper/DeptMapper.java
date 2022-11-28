package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.user.infrastructure.repo.dataobject.DeptDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName MenuMapper
 * Description MenuMapper
 * @Author 2456910384
 * @Date 2022/10/13 14:17
 * @Version 1.0
 */
@Mapper
public interface DeptMapper extends UserBaseMapper<DeptDO> {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(DeptDO dataObject);

}
