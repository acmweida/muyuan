package com.muyuan.system.domain.entity;

import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.model.SysUserRole;
import lombok.Data;

import java.util.List;

@Data
public class SysUserEntity extends SysUser {


    /**
     * 用户角色
     */
    private List<SysUserRole> sysUserRoles;

    private List<SysRole> sysRoles;


}
