package com.muyuan.member.domain.service;

import java.util.List;
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
     * @param roleNames
     * @return
     */
    Set<String> selectMenuPermissionByRoleNames(List<String> roleNames);

}
