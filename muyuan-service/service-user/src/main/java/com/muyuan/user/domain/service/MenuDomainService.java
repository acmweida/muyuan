package com.muyuan.user.domain.service;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.MenuCommand;
import com.muyuan.user.face.dto.MenuQueryCommand;

import java.util.List;
import java.util.Optional;

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
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * 获取菜单
     * @param userID
     * @param platformType
     * @return
     */
    List<Menu> getMenuByUserId(UserID userID, PlatformType platformType);

    /**
     * 获取菜单
     * @param command
     * @return
     */
    List<Menu> getMenuByRoleCodes(MenuQueryCommand command);

    /**
     * 列表查询
     * @param command
     * @return
     */
    List<Menu> list(MenuQueryCommand command);


    Optional<Menu> getMenu(Long id);

    /**
     * 检查唯一性
     *
     * @param key
     * @return
     */
    String checkUnique(Menu.Identify key);


    boolean updateMenu(MenuCommand command);

    boolean addMenu(MenuCommand command);

    boolean deleteMenuById(Long... ids);
}
