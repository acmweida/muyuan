package com.muyuan.user.service;

import com.muyuan.user.api.dto.MenuDTO;

import java.util.List;

/**
 * @ClassName MenuService 接口
 * Description 菜单接口
 * @Author 2456910384
 * @Date 2022/10/13 10:59
 * @Version 1.0
 */
public interface MenuService {

    /**
     *  获取角色菜单
     * @return
     */
    List<MenuDTO> getMenu();
}
