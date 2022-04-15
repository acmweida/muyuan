package com.muyuan.system.application.service;

import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName MenuService 接口
 * Description MenuService
 * @Author 2456910384
 * @Date 2022/2/14 9:38
 * @Version 1.0
 */
public interface SysMenuService {

    /**
     * 列表查询
     * @param sysMenuDTO
     * @return
     */
    List<SysMenu> list(SysMenuDTO sysMenuDTO);

    /**
     * 根据Id获取菜单详情
     * @param id
     * @return
     */
    Optional<SysMenu> get(String id);
}
