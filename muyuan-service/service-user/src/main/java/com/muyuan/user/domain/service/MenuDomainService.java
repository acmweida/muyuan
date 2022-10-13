package com.muyuan.user.domain.service;

import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.face.dto.MenuQueryCommand;

import java.util.List;

/**
 * @ClassName PermissionDomainService 接口
 * Description 权限接口
 * @Author 2456910384
 * @Date 2022/10/11 14:07
 * @Version 1.0
 */
public interface MenuDomainService {

    /**
     * 获取菜单
     * @param request
     * @return
     */
    List<Menu> getMenuByRoleCodes(MenuQueryCommand request);

}
