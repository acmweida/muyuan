package com.muyuan.manager.system.service.service.impl;

import com.muyuan.manager.system.model.SysRole;
import com.muyuan.manager.system.model.SysUser;
import com.muyuan.manager.system.dto.assembler.SysUserInfoAssembler;
import com.muyuan.manager.system.dto.vo.SysUserVO;
import com.muyuan.manager.system.service.MenuService;
import com.muyuan.manager.system.service.RoleService;
import com.muyuan.manager.system.service.SysUsernService;
import com.muyuan.manager.system.service.service.SysUserApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SysUserApplicationServiceImpl implements SysUserApplicationService {

    private SysUsernService sysUsernService;

    private RoleService roleService;

    private MenuService menuService;

    @Override
    public Optional<SysUserVO> get(Long id) {
        final Optional<SysUser> userInfo = sysUsernService.getByyId(id);
        if (!userInfo.isPresent()) {
            log.info("userId :{} 未找到", id);
            return Optional.empty();
        }

        List<SysRole> roles = roleService.getRoleByUserId(id);

        SysUserVO sysUserVO = SysUserInfoAssembler.buildUserVO(userInfo.get(), roles);
        return Optional.of(sysUserVO);
    }

}
