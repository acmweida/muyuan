package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.MenuInterface;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;


/**
 * @ClassName MenuInterfaceApi
 * Description 菜单接口
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = MenuInterface.class
)
public class MenuInterfaceApi implements MenuInterface {

    private MenuMapper MENU_MAPPER;

    private MenuDomainService menuDomainService;

    @Override
    public Result<List<MenuDTO>> getMenuByRoleCods(MenuQueryRequest request) {

        List<Menu> Menu = menuDomainService.getMenuByRoleCodes(MENU_MAPPER.toCommand(request));

        return ResultUtil.success(MENU_MAPPER.toDTO(Menu));
    }

    @Override
    public Result<List<MenuDTO>> list(MenuQueryRequest request) {
        List<Menu> list = menuDomainService.list(MENU_MAPPER.toCommand(request));
        return ResultUtil.success(MENU_MAPPER.toDTO(list));
    }
}
