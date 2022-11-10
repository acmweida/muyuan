package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName MenuMapper
 * Description MenuMapper
 * @Author 2456910384
 * @Date 2022/10/13 14:17
 * @Version 1.0
 */
@Mapper
public interface MenuMapper extends UserBaseMapper<MenuDO> {

    /**
     * 根据 rodeCode platform 查询菜单
     * @param roleCode
     * @param platformType
     * @return
     */
    List<MenuDO> selectByRoleCode(@Param("roleCode") String roleCode,@Param("platformType") Integer platformType);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(MenuDO dataObject);
}
