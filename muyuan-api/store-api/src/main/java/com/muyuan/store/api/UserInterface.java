package com.muyuan.store.api;


import com.muyuan.common.core.result.Result;
import com.muyuan.store.interfaces.to.UserTO;

import java.util.List;
import java.util.Set;


public interface UserInterface {

    /**
     * 通过账号获取用户信息
     *
     * @param username 用户名
     * @return
     */
    Result<UserTO> getUserByUsername(String username);

    /**
     * 通过角色获取权限集合
     *
     * @param roleIds
     * @return
     */
    Set<String> getMenuPermissionByRoleCodes(List<String> roleIds);


    /**
     * 账户关联店铺ID
     *
     * @param shopId
     * @param shopId
     * @return
     */
    void linkShop(Long shopId);
}
