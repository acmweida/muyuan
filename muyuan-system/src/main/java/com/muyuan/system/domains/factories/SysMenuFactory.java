package com.muyuan.system.domains.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domains.model.SysMenu;
import com.muyuan.system.domains.dto.SysMenuDTO;

import java.util.Date;

/**
 * @ClassName SysMenuFactory
 * Description 系统菜单工厂
 * @Author 2456910384
 * @Date 2022/4/21 15:39
 * @Version 1.0
 */
public class SysMenuFactory {

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public static SysMenu newInstance(SysMenuDTO sysMenuDTO)  {
        SysMenu sysMenu = sysMenuDTO.convert();
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateBy(SecurityUtils.getUserId());
        return sysMenu;
    }

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public static SysMenu buildEntity(SysMenuDTO sysMenuDTO)  {
        SysMenu sysMenuEntity =sysMenuDTO.convert();
        sysMenuEntity.setId(Long.valueOf(sysMenuDTO.getId()));
        return sysMenuEntity;
    }
}
