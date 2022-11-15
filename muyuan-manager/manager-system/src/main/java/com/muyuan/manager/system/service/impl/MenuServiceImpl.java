package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.manager.system.service.MenuService;
import com.muyuan.user.api.MenuInterface;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.api.dto.MenuRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

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

    // ##############################  query ########################## //

    @Override
    public List<MenuDTO> list(MenuQueryParams params) {

        Result<List<MenuDTO>> result = menuInterface.list(MenuQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .platformType(params.getPlatformType() == null ? PlatformType.OPERATOR : PlatformType.trance(params.getPlatformType()))
                .build());

        return ResultUtil.getOr(result, ArrayList::new);
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

    @Override
    public Optional<MenuDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<MenuDTO> dictType = menuInterface.getMenu(id_);
                    return ResultUtil.getOr(dictType, null);
                });
    }

    // ##############################  query ########################## //

    @Override
    public Result add(MenuRequest menuRequest) {
        menuRequest.setCreateBy(SecurityUtils.getUserId());
        return menuInterface.addMenu(menuRequest);
    }

    @Override
    public Result update(MenuRequest menuRequest) {
        menuRequest.setUpdateBy(SecurityUtils.getUserId());
        return menuInterface.updateMenu(menuRequest);
    }

    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return menuInterface.deleteMenu(ids);
    }

}
