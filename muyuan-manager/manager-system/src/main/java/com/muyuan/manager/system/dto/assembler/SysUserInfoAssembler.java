package com.muyuan.manager.system.dto.assembler;

import com.muyuan.manager.system.model.SysRole;
import com.muyuan.manager.system.model.SysUser;
import com.muyuan.manager.system.dto.vo.SysUserVO;
import com.muyuan.user.api.dto.OperatorDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class SysUserInfoAssembler {

    public static OperatorDTO buildUserTO(SysUser user) {
        OperatorDTO operatorDTO = new OperatorDTO();
        BeanUtils.copyProperties(user, operatorDTO);
        return operatorDTO;
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
