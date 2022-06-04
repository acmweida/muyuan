package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.interfaces.dto.SysRoleDTO;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @ClassName SysRoleFactory
 * Description 用户工厂
 * @Author 2456910384
 * @Date 2022/4/28 17:05
 * @Version 1.0
 */
public class SysRoleFactory {

    public static SysRole newInstance(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = sysRoleDTO.convert();
        sysRole.setCreateTime(new Date());
        sysRole.setCreateBy(SecurityUtils.getUserId());
        // 默认启用
        if (ObjectUtils.isEmpty(sysRole.getStatus())) {
            sysRole.setStatus("0");
        }
        return sysRole;
    }


}
