package com.muyuan.system.interfaces.assembler;

import com.muyuan.system.domain.vo.SysUserVO;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.interfaces.to.SysUserTO;
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
