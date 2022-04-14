package com.muyuan.common.member.application.query;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.member.domain.entity.RoleEntity;
import com.muyuan.common.member.domain.model.Menu;
import com.muyuan.common.member.domain.repo.MenuRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MenuQueryImpl
 * Description 权限查询
 * @Author 2456910384
 * @Date 2022/2/9 16:52
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class MenuQuery {

    public MenuRepo menuRepo;;

    /**
     * 通过角色名称查询权限
     * @param roleNames
     * @return
     */
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (RoleEntity.isShopKeeper(roleNames)) {
            perms.add(SecurityConst.ALL_PERMISSION);
        } else {
            List<String> permList = menuRepo.selectMenuPermissionByRoleNames(roleNames);
            for (Iterator<String> iterator = permList.iterator(); iterator.hasNext();) {
                perms.add(iterator.next());
            }
        }
        return perms;
    }

    /**
     *通过角色名称查询目录 菜单
     * @param roleNames
     * @return
     */
    public List<Menu>  selectMenuByRoleNames(List<String> roleNames) {
        return menuRepo.selectMenuByRoleNames(roleNames);
    }

}
