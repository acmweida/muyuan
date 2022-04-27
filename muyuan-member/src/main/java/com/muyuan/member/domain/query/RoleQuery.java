package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.repo.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName RoleQueryImpl
 * Description 角色信息查询
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
@AllArgsConstructor
@Service
public class RoleQuery {

    private RoleRepo roleRepo;;

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    public List<Role> getRoleByUserId(Long userId) {
        Assert.notNull(userId,"user Id is null");
        return roleRepo.selectRoleByUserId(userId);
    }

}
