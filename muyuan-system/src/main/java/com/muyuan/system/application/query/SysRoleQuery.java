package com.muyuan.system.application.query;


import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.repo.SysRoleRepo;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.core.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleQueryImpl
 * Description 角色信息查询
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class SysRoleQuery {

   private SysRoleRepo sysRoleRepo;

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
   public List<SysRole> getRoleByUserId(Long userId) {
        Assert.isNonEmpty(userId);
        return sysRoleRepo.selectRoleByUserId(userId);
    }

}
