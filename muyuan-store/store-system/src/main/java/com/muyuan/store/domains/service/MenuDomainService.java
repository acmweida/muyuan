package com.muyuan.store.domains.service;

import com.muyuan.store.domains.model.Menu;
import com.muyuan.store.domains.dto.MenuDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @ClassName MenuDomainService 接口
 * Description 菜单
 * @Author 2456910384
 * @Date 2022/2/14 9:38
 * @Version 1.0
 */
public interface MenuDomainService {

    /**
     * 获取权限信息
     * @param roleCodes
     * @return
     */
    Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes);

    /**
     * 列表查询
     * @param sysMenuDTO
     * @return
     */
    List<Menu> list(MenuDTO sysMenuDTO);


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
    Optional<Menu> get(String id);

    /**
     * 通过角色获取菜单列表
     * @param roleCodes
     * @return
     */
    List<Menu> selectMenuByRoleCodes(List<String> roleCodes);

    /**
     * 菜单添加
     * @param sysMenuDTO
     * @return
     */
    void add(MenuDTO sysMenuDTO);

    /**
     * 更新
     * @param sysMenuDTO
     * @return
     */
    void update(MenuDTO sysMenuDTO);

    /**
     * 校验唯一性
     * @param sysMenu
     * @return
     */
    String checkMenuNameUnique(Menu sysMenu);

    /**
     * 删除
     * @param ids
     * @return
     */
    void deleteById(String... ids);

}
