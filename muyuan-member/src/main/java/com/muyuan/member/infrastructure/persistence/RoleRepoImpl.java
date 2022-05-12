package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.RoleMenu;
import com.muyuan.member.domain.repo.RoleRepo;
import com.muyuan.member.infrastructure.persistence.dao.RoleMapper;
import com.muyuan.member.infrastructure.persistence.dao.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleRepoImpl
 * Description RoleRepoImpl
 * @Author 2456910384
 * @Date 2021/12/24 11:20
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class RoleRepoImpl implements RoleRepo {

    private RoleMapper roleMapper;

    private RoleMenuMapper roleMenuMapper;


    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> select(Map params) {
        return roleMapper.selectList(params);
    }

    @Override
    public Role selectOne(Map params) {
        return roleMapper.selectOne(params);
    }

    @Override
    public void insert(Role sysRole) {
        roleMapper.insert(sysRole);
    }

    @Override
    public void batchInsert(List<RoleMenu> sysRoleMenus) {
        roleMenuMapper.batchInsert(sysRoleMenus);
    }

    @Override
    public void updateById(Role role) {
        roleMapper.updateBy(role,"id");
    }

    @Override
    public void deleteMenuByRoleId(Long  roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return;
        }
        roleMenuMapper.deleteBy(new SqlBuilder().eq(
                "roleId",roleId
        ).build());
    }

    @Override
    public void deleteById(String... id) {
        roleMenuMapper.deleteBy(
                new SqlBuilder().in("id",id)
                        .build()
        );
    }
}
