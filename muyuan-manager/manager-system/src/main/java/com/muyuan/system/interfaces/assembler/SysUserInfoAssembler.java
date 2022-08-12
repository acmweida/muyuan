package com.muyuan.system.interfaces.assembler;

import com.muyuan.menager.interfaces.to.SysUserTO;
import com.muyuan.system.domains.model.SysRole;
import com.muyuan.system.domains.model.SysUser;
import com.muyuan.system.domains.vo.SysUserVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class SysUserInfoAssembler {

    public static SysUserTO buildUserTO(SysUser user) {
        SysUserTO userDTO = new SysUserTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static SysUserVO buildUserVO(SysUser user, List<String> roleNames, Set<String> perms) {
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);

        sysUserVO.setRoles(roleNames);
        sysUserVO.setPermissions(perms);
        return sysUserVO;
    }

    public static SysUserVO buildUserVO(SysUser user, List<SysRole> roles) {
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);

        sysUserVO.setSysRoles(roles);
        return sysUserVO;
    }
}
