package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.face.dto.MenuQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.MenuConverter;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import com.muyuan.user.infrastructure.repo.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

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

    @Override
    public List<Menu> list(MenuQueryCommand command) {
        List<MenuDO> menuDOS = menuMapper.selectList(new SqlBuilder(MenuDO.class)
                .like(NAME, command.getName())
                .eq(STATUS, command.getStatus())
                .eq(TYPE, command.getPlatformType().getCode())
                .orderByAsc(ORDER_NUM)
                .build());

        return converter.to(menuDOS);
    }
}
