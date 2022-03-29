package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.application.vo.SysMenuVO;
import com.muyuan.system.interfaces.facade.controller.SysMenuController;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MenuControllerImpl
 * Description MenuControllerImpl 菜单控制器实现
 * @Author 2456910384
 * @Date 2022/2/11 16:12
 * @Version 1.0
 */
@Component
public class SysMenuControllerImpl implements SysMenuController {


    @Override
    public Result<List<SysMenuVO>> getMenus() {
        return null;
    }
}
