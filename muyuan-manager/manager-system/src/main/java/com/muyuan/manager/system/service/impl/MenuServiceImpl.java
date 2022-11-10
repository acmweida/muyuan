package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.domains.model.SysMenu;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.repo.SysMenuRepo;
import com.muyuan.manager.system.dto.MenuParams;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.manager.system.dto.converter.MenuConverter;
import com.muyuan.manager.system.service.MenuService;
import com.muyuan.user.api.MenuInterface;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.api.dto.MenuRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SysMenuServiceImpl
 * Description 菜单服务实现
 * @Author 2456910384
 * @Date 2022/4/15 14:17
 * @Version 1.0
 */
@Component
@Slf4j
public class MenuServiceImpl implements MenuService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private MenuInterface menuInterface;

    @Autowired
    private SysMenuRepo sysMenuRepo;

    @Autowired
    private MenuConverter converter;

    // ##############################  query ########################## //

    /**
     * 通过角色名称查询权限
     *
     * @param roleCodes
     * @return
     */
    @Override
    public Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        Set<String> perms = new HashSet<>();
        if (SysRole.isAdmin(roleCodes)) {
            perms.add(SecurityConst.ALL_PERMISSION);
        } else {
            List<String> permList = sysMenuRepo.selectMenuPermissionByRoleCodes(roleCodes);
            for (Iterator<String> iterator = permList.iterator(); iterator.hasNext(); ) {
                perms.add(iterator.next());
            }
        }
        return perms;
    }


    @Override
    public List<MenuDTO> list(MenuQueryParams params) {

        Result<List<MenuDTO>> result = menuInterface.list(MenuQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .platformType(params.getPlatformType() == null ? PlatformType.OPERATOR : PlatformType.trance(params.getPlatformType()))
                .build());

        return   ResultUtil.getOr(result,ArrayList::new);
    }

    @Override
    public List<Long> listSelectIdByRoleId(String... roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return Collections.EMPTY_LIST;
        }

        Result<List<MenuDTO>> menuByRoleCods = menuInterface.getMenuByRoleCods(MenuQueryRequest.builder()
                .roleCodes(roleIds)
                .build());
        return Objects.requireNonNull(ResultUtil.getOr(menuByRoleCods, ArrayList::new))
                .stream().map(MenuDTO::getId).collect(Collectors.toList());
    }

    /**
     * 查询
     * condition
     * id
     * name
     *
     * @param sysMenu
     * @return
     */
    public SysMenu get(SysMenu sysMenu) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysMenu.class)
                .eq("status", "0")
                .eq("id", sysMenu.getId())
                .eq("name", sysMenu.getName());
        return sysMenu = sysMenuRepo.selectOne(sqlBuilder.build());
    }

    @Override
    public Optional<MenuDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<MenuDTO> dictType = menuInterface.getMenu(id_);
                    return ResultUtil.getOr(dictType,null);
                });
    }

    // ##############################  query ########################## //

    @Override
    public Result add(MenuParams menuParams) {
        MenuRequest menuRequest = converter.toRequest(menuParams);
        menuRequest.setCreateBy(SecurityUtils.getUserId());
        return menuInterface.addMenu(menuRequest);
    }

    @Override
    public Result update(MenuParams menuParams) {
        MenuRequest menuRequest = converter.toRequest(menuParams);
        menuRequest.setUpdateBy(SecurityUtils.getUserId());
        return menuInterface.updateMenu(menuRequest);
    }

    @Override
    @Transactional
    public void deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        List<String> removeIds = new ArrayList<>(Arrays.asList(ids));
        List<SysMenu> menuList;
        do {
            menuList = sysMenuRepo.select(new SqlBuilder()
                    .select("id")
                    .in("parentId", ids)
                    .build());
            if (menuList.isEmpty()) {
                List<String> collect = menuList.stream().map(SysMenu::getId)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
                removeIds.addAll(collect);
                ids = collect.toArray(new String[0]);
            }
        } while (!menuList.isEmpty());

        sysMenuRepo.deleteById(removeIds.toArray(new String[0]));
        sysMenuRepo.refreshCache();
    }

}
