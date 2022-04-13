package com.muyuan.system.application.query;


import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.repo.SysMenuRepo;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MenuQuery
 * Description 菜单查询
 * @Author 2456910384
 * @Date 2022/2/9 16:21
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class SysMenuQuery {

    private SysMenuRepo sysMenuRepo;

    /**
     * 通过角色名称查询权限
     * @param roleNames
     * @return
     */
   public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
       Set<String> perms = new HashSet<>();
       if (SysRoleEntity.isAdmin(roleNames)) {
           perms.add(SecurityConst.ALL_PERMISSION);
       } else {
           List<String> permList = sysMenuRepo.selectMenuPermissionByRoleNames(roleNames);
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
   public List<SysMenu>  selectMenuByRoleNames(List<String> roleNames) {
       return sysMenuRepo.selectMenuByRoleNames(roleNames);
   }

}
