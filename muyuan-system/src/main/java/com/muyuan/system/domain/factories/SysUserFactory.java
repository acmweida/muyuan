package com.muyuan.system.domain.factories;

import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.domain.repo.SysUserRepo;

public class SysUserFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static SysUserEntity newSysUserEntity(RegisterDTO registerDTO)  {
        SysUserEntity sysUserEntity = new SysUserEntity(registerDTO.getUsername(), registerDTO.getPassword());
        sysUserEntity.initInstance();;
        return sysUserEntity;
    }
}
