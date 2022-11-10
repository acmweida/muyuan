package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.user.api.MenuInterface;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.api.dto.MenuRequest;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Optional;


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

    private MenuDomainService menuService;

    @Override
    public Result<List<MenuDTO>> getMenuByRoleCods(MenuQueryRequest request) {

        List<Menu> Menu = menuService.getMenuByRoleCodes(MENU_MAPPER.toCommand(request));

        return ResultUtil.success(MENU_MAPPER.toDTO(Menu));
    }

    @Override
    public Result<List<MenuDTO>> list(MenuQueryRequest request) {
        List<Menu> list = menuService.list(MENU_MAPPER.toCommand(request));
        return ResultUtil.success(MENU_MAPPER.toDTO(list));
    }

    @Override
    public Result<MenuDTO> getMenu(Long id) {
        Optional<Menu> handler = menuService.getMenu(id);

        return handler.map(MENU_MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateMenu(MenuRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(menuService.checkUnique(new Menu(request.getName(),request.getParentId())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        if (GlobalConst.YES_FRAME.equals(request.getFrame()) && StrUtil.ishttp(request.getPath())) {
            return ResultUtil.fail("新增菜单[{}]失败，地址必须以http(s)://开头", request.getName());
        }

        boolean flag = menuService.updateMenu(MENU_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result addMenu(MenuRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(menuService.checkUnique(new Menu(request.getName(),request.getParentId())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        if (GlobalConst.YES_FRAME.equals(request.getFrame()) && StrUtil.ishttp(request.getPath())) {
            return ResultUtil.fail("新增菜单[{}]失败，地址必须以http(s)://开头", request.getName());
        }
        boolean flag = menuService.addMenu(MENU_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }
}
