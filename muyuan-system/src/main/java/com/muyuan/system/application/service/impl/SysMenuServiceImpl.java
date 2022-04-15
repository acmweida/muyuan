package com.muyuan.system.application.service.impl;

import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.application.service.SysMenuService;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName SysMenuServiceImpl
 * Description 菜单服务实现
 * @Author 2456910384
 * @Date 2022/4/15 14:17
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuQuery sysMenuQuery;

    @Override
    public List<SysMenu> list(SysMenuDTO sysMenuDTO) {
        return sysMenuQuery.list(sysMenuDTO);
    }

    @Override
    public Optional<SysMenu> get(String id) {
        SysMenu sysMenu = sysMenuQuery.get(id);
        if (null != sysMenu) {
            return Optional.of(sysMenu);
        }
        return Optional.empty();
    }
}
