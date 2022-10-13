package com.muyuan.manager.system.domains.service;

import com.muyuan.manager.system.domains.model.SysMenu;
import com.muyuan.manager.system.domains.dto.SysMenuDTO;

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
public interface SysMenuDomainService {

    /**
     * 列表查询
     * @param sysMenuDTO
     * @return
     */
    List<SysMenu> list(SysMenuDTO sysMenuDTO);


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
    Optional<SysMenu> get(String id);

    /**
     * 获取权限信息
     * @param roleCodes
     * @return
     */
    Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes);


    /**
     * 菜单添加
     * @param sysMenuDTO
     * @return
     */
    void add(SysMenuDTO sysMenuDTO);

    /**
     * 更新
     * @param sysMenuDTO
     * @return
     */
    void update(SysMenuDTO sysMenuDTO);

    /**
     * 校验唯一性
     * @param sysMenu
     * @return
     */
    String checkMenuNameUnique(SysMenu sysMenu);

    /**
     * 删除
     * @param ids
     * @return
     */
    void deleteById(String... ids);
}
