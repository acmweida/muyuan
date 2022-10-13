package com.muyuan.user.domain.repo;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;

import java.util.List;

public interface MenuRepo {

    /**
     * 角色菜单查询
     * @param roleCode
     * @param platformType
     * @return
     */
    List<Menu> selectByRoleCode(RoleCode roleCode, PlatformType platformType);

}
