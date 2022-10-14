package com.muyuan.user.api;

import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;

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
}
