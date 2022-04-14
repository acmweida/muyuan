package com.muyuan.system.interfaces.assembler;

import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import com.muyuan.system.domain.model.SysUser;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class SysUserInfoAssembler {

    public static SysUserDTO buildUserDTO(SysUser user) {
        SysUserDTO userDTO = new SysUserDTO();
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
}
