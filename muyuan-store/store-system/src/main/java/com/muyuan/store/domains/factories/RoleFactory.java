package com.muyuan.store.domains.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.store.domains.model.Role;
import com.muyuan.store.domains.dto.RoleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @ClassName SysRoleFactory
 * Description 用户工厂
 * @Author 2456910384
 * @Date 2022/4/28 17:05
 * @Version 1.0
 */
public class RoleFactory {

    public static Role newSysRole(RoleDTO sysRoleDTO) {
        Role sysRole = new Role();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRole.setCreateTime(new Date());
        sysRole.setCreateBy(SecurityUtils.getUserId());
        // 默认启用
        if (ObjectUtils.isEmpty(sysRole.getStatus())) {
            sysRole.setStatus("0");
        }
        return sysRole;
    }

    public static Role updateSysRole(RoleDTO sysRoleDTO) {
        Role sysRole = new Role();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRole.setUpdateTime(new Date());
        sysRole.setUpdateBy(SecurityUtils.getUserId());
        sysRole.setId(sysRoleDTO.getId());
        return sysRole;
    }
}
