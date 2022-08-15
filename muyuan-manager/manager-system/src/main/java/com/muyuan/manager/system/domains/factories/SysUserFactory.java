package com.muyuan.manager.system.domains.factories;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.domains.model.SysUser;
import com.muyuan.manager.system.domains.dto.SysUserDTO;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

public class SysUserFactory {

    /**
     * 构建一个新用户实体 并初始化
     *
     * @return
     */
    public static SysUser newInstance(SysUserDTO registerDTO) {
        SysUser sysUser = registerDTO.convert();
        init(sysUser);
        return sysUser;
    }

    /**
     * 初始化用户信息
     */
    private static void init(SysUser sysUser) {
        Assert.isTrue(!ObjectUtils.isEmpty(sysUser.getPassword()), "SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        sysUser.setPassword(EncryptUtil.SHA1(sysUser.getPassword() + salt, encryptKey));

        sysUser.setSalt(salt);
        sysUser.setEncryptKey(encryptKey);

        sysUser.setCreateTime(new Date());
        sysUser.setCreateBy(SecurityUtils.getUserId());
    }
}
