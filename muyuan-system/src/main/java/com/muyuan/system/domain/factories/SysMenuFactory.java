package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysMenuEntity;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import org.springframework.beans.BeanUtils;

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
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO,sysMenu);
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateBy(SecurityUtils.getUserId());
        return sysMenu;
    }

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public static SysMenuEntity buildEntity(SysMenuDTO sysMenuDTO)  {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(sysMenuDTO,sysMenuEntity);
        sysMenuEntity.setId(Long.valueOf(sysMenuDTO.getId()));
        return sysMenuEntity;
    }
}
