package com.muyuan.member.domain.service;

import com.muyuan.member.domain.model.Role;

import java.util.List;

/**
 * @ClassName RoleQueryImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
public interface RoleDomainService {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
     List<Role> getRoleByUserId(Long userId);

}
