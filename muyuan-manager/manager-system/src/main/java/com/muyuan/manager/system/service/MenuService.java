package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Result;
import com.muyuan.manager.system.dto.MenuParams;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.user.api.dto.MenuDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    /**
     * 列表查询
     * @param roleId
     * @return
     */
    List<Long> listSelectIdByRoleId(String... roleId);

    /**
     * 根据Id获取菜单详情
     * @param id
     * @return
     */
    Optional<MenuDTO> get(Long id);

    /**
     * 获取权限信息
     * @param roleCodes
     * @return
     */
    Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes);


    /**
     * 菜单添加
     * @param menuParams
     * @return
     */
    Result add(MenuParams menuParams);

    /**
     * 更新
     * @param menuParams
     * @return
     */
    Result update(MenuParams menuParams);


    /**
     * 删除
     * @param ids
     * @return
     */
    void deleteById(String... ids);
}
