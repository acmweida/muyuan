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

    Set<String> selectMenuPermissionByRoleNames(List<String> roleNames);

    List<Menu>  selectMenuByRoleNames(List<String> roleNames);
}
