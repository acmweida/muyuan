package com.muyuan.store.domains.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.store.domains.model.Menu;
import com.muyuan.store.domains.dto.MenuDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName SysMenuFactory
 * Description 系统菜单工厂
 * @Author 2456910384
 * @Date 2022/4/21 15:39
 * @Version 1.0
 */
public class MenuFactory {

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public static Menu newSysMenu(MenuDTO sysMenuDTO)  {
        Menu sysMenu = new Menu();
        BeanUtils.copyProperties(sysMenuDTO,sysMenu);
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateBy(SecurityUtils.getUserId());
        return sysMenu;
    }

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public static Menu updateSysMenu(MenuDTO sysMenuDTO)  {
        Menu sysMenu = new Menu();
        BeanUtils.copyProperties(sysMenuDTO,sysMenu);
        sysMenu.setUpdateTime(new Date());
        sysMenu.setUpdateBy(SecurityUtils.getUserId());
        sysMenu.setId(Long.valueOf(sysMenuDTO.getId()));
        return sysMenu;
    }
}
