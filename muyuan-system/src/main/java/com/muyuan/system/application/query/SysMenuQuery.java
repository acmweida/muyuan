package com.muyuan.system.application.query;


import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.repo.SysMenuRepo;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
@Component
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


   public List<SysMenu> list(SysMenuDTO sysMenuDTO) {
       SqlBuilder sqlBuilder= new SqlBuilder(SysMenu.class);
       if (StrUtil.isNotBlank(sysMenuDTO.getName())) {
           sqlBuilder.eq("name",sysMenuDTO.getName());
       }
       if (StrUtil.isNotBlank(sysMenuDTO.getStatus())) {
           sqlBuilder.eq("status",sysMenuDTO.getStatus());
       }
       List<SysMenu> list = sysMenuRepo.select(sqlBuilder.build());
       return list;
   }

   public SysMenu get(String id) {
       SqlBuilder sqlBuilder= new SqlBuilder(SysMenu.class);
       sqlBuilder.eq("status","1")
               .eq("id",id);
       return sysMenuRepo.selectOne(sqlBuilder.build());
   }

}
