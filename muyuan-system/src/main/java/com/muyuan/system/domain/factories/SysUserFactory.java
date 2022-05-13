package com.muyuan.system.domain.factories;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

public class SysUserFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static SysUser newSysUser(RegisterDTO registerDTO)  {
        SysUser sysUser = new SysUser(registerDTO.getUsername(), registerDTO.getPassword());
        init(sysUser);
        return sysUser;
    }

    /**
     * 初始化用户信息
     */
    private static void init(SysUser sysUser) {
        Assert.isTrue(!ObjectUtils.isEmpty(sysUser.getPassword()),"SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        sysUser.setNickName(SysUserEntity.createUserName());
        sysUser.setPassword(EncryptUtil.SHA1(sysUser.getPassword() + salt, encryptKey));;
        sysUser.setSalt(salt);
        sysUser.setEncryptKey(encryptKey);

        sysUser.setCreateTime(new Date());
        sysUser.setCreateBy(SecurityUtils.getUserId());
    }
}
