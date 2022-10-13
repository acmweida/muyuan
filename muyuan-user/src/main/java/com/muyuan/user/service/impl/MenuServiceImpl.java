package com.muyuan.user.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.user.api.MenuInterface;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.service.MenuService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * Description 菜单 Service
 * @Author 2456910384
 * @Date 2022/10/13 15:36
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private MenuInterface menuInterface;

    @Override
    public List<MenuDTO> getMenu() {
        List<String> roles = SecurityUtils.getRoles();
        PlatformType platformType = PlatformType.valueOf(SecurityUtils.getPlatformType());

        Result<List<MenuDTO>> meus = menuInterface.getMenuByRoleCods(MenuQueryRequest.builder()
                .roleCodes(roles.toArray(new String[0]))
                .platformType(platformType)
                .build());

        return ResultUtil.getOr(meus, ArrayList::new);
    }
}
