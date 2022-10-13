package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.infrastructure.repo.converter.MenuConverter;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import com.muyuan.user.infrastructure.repo.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MenuRepoImpl
 * Description MenuRepoImpl
 * @Author 2456910384
 * @Date 2022/10/13 14:13
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class MenuRepoImpl implements MenuRepo {

    private MenuMapper menuMapper;

    private MenuConverter converter;

    @Override
    public List<Menu> selectByRoleCode(RoleCode roleCode, PlatformType platformType) {
        List<MenuDO> menuDOS = menuMapper.selectByRoleCode(roleCode.getValue(), platformType.getCode());
        return converter.to(menuDOS);
    }
}
