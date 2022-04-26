package com.muyuan.system.domain.service.impl;

import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.domain.service.SysRoleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.Assert;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SysRoleDomainServiceImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:41
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class SysRoleDomainServiceImpl implements SysRoleDomainService {


    private SysRoleRepo sysRoleRepo;

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        Assert.isNonEmpty(userId);
        return sysRoleRepo.selectRoleByUserId(userId);
    }
}
