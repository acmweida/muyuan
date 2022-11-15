package com.muyuan.user.api;

import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.api.dto.MenuRequest;

import java.util.List;

/**
 * @ClassName MenuInterface
 * Description 菜单接口
 * @Author 2456910384
 * @Date 2022/10/13 11:06
 * @Version 1.0
 */
public interface MenuInterface {

    /**
     * 通过用户ID 查询菜单
     * @param request
     * @return
     */
    Result<List<MenuDTO>> getMenuByRoleCods(MenuQueryRequest request);

    /**
     * 菜单列表查询
     * @param request
     * @return
     */
    Result<List<MenuDTO>> list(MenuQueryRequest request);


    Result<MenuDTO> getMenu(Long id);

    /**
     * 更新字典数据
     * @param request
     * @return
     */
    Result updateMenu(MenuRequest request);


    /**
     * 添加菜单
     * @param request
     * @return
     */
    Result addMenu(MenuRequest request);

    /**
     *  删除菜单
     * @param ids
     * @return
     */
    Result deleteMenu(Long... ids);

}
