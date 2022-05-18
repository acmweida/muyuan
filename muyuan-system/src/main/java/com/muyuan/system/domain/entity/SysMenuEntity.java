package com.muyuan.system.domain.entity;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.SysMenu;

import java.util.Date;

/**
 * @ClassName SysMenuEntity
 * Description 菜单实体域
 * @Author 2456910384
 * @Date 2022/4/21 15:16
 * @Version 1.0
 */
public class SysMenuEntity extends SysMenu {

    /**
     *  构建一个系统菜单 并初始化
     * @return
     */
    public SysMenuEntity update()  {
        this.setUpdateTime(new Date());
        this.setUpdateBy(SecurityUtils.getUserId());
        return this;
    }

}
