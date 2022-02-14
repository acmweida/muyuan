package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.Menu;

import java.util.List;
import java.util.Set;

/**
 * @ClassName MenuQuery
 * Description 菜单查询
 * @Author 2456910384
 * @Date 2022/2/9 16:21
 * @Version 1.0
 */
public interface MenuQuery {

    /**
     * 通过角色名称查询权限
     * @param roleNames
     * @return
     */
    Set<String> selectMenuPermissionByRoleNames(List<String> roleNames);

    /**
     *通过角色名称查询目录 菜单
     * @param roleNames
     * @return
     */
    List<Menu>  selectMenuByRoleNames(List<String> roleNames);
}
