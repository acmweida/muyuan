package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.Role;

import java.util.List;

public interface RoleQuery {
    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(Long userId);

}
