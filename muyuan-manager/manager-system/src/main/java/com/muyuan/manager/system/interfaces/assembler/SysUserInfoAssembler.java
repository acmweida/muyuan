package com.muyuan.manager.system.interfaces.assembler;

import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.model.SysUser;
import com.muyuan.manager.system.domains.vo.SysUserVO;
import com.muyuan.user.api.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class SysUserInfoAssembler {

    public static UserDTO buildUserTO(SysUser user) {
        UserDTO userDTO = new UserDTO();
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
