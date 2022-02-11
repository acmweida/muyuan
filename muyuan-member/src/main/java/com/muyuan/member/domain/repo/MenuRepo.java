package com.muyuan.member.domain.repo;

import java.util.List;

/**
 * @ClassName MenuRepo
 * Description 菜单权限 MenuRepo
 * @Author 2456910384
 * @Date 2022/2/11 16:28
 * @Version 1.0
 */
public interface MenuRepo {

    List<String>  selectMenuPermissionByRoleNames(List<String> roleNames);
}
