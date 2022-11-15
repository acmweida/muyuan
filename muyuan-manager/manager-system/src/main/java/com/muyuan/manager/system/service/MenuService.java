package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Result;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuRequest;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName MenuService 接口
 * Description MenuService
 * @Author 2456910384
 * @Date 2022/2/14 9:38
 * @Version 1.0
 */
public interface MenuService {

    /**
     * 列表查询
     * @param menuQueryParams
     * @return
     */
    List<MenuDTO> list(MenuQueryParams menuQueryParams);

    Optional<MenuDTO> get(Long id);

    /**
     * 列表查询
     * @param roleId
     * @return
     */
    List<Long> listSelectIdByRoleId(String... roleId);


    /**
     * 菜单添加
     * @param request
     * @return
     */
    Result add(MenuRequest request);

    /**
     * 更新
     * @param menuParams
     * @return
     */
    Result update(MenuRequest menuParams);


    /**
     * 删除
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);
}
