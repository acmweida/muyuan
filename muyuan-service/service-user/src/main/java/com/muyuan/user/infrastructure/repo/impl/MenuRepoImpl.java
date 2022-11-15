package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.face.dto.MenuQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.MenuConverter;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import com.muyuan.user.infrastructure.repo.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
                .in(PARENT_ID, command.getParentIds())
                .eq(PLATFORM_TYPE, ObjectUtils.isEmpty(command.getPlatformType()) ? null : command.getPlatformType().getCode())
                .orderByAsc(ORDER_NUM)
                .build());

        return converter.to(menuDOS);
    }

    @Override
    public Menu selectMenu(MenuID id) {
        MenuDO menuDO = menuMapper.selectOne(new SqlBuilder(MenuDO.class)
                .eq(ID, id.getValue())
                .build());
        return converter.to(menuDO);
    }

    @Override
    public Menu selectMenu(Menu menu) {
        MenuDO menuDO = menuMapper.selectOne(new SqlBuilder(MenuDO.class).select(ID)
                .eq(NAME, menu.getName())
                .eq(TYPE, menu.getType())
                .eq(PARENT_ID, menu.getParentId())
                .eq(ID, ObjectUtils.isEmpty(menu.getId()) ? menu.getId() : menu.getId().getValue())
                .eq(PLATFORM_TYPE, menu.getPlatformType().getCode())
                .build());

        return converter.to(menuDO);
    }

    @Override
    public Menu updateDMenu(Menu menu) {
        SqlBuilder sqlBuilder = new SqlBuilder(MenuDO.class)
                .eq(ID, menu.getId().getValue());

        MenuDO menuDO = menuMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(menuDO)) {
            menuMapper.updateBy(converter.to(menu), ID);
        }

        return converter.to(menuDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(Menu menu) {
        MenuDO to = converter.to(menu);
        Integer count = menuMapper.insertAuto(to);

        // 默认管理员权限
//        sysRoleMenuMapper.insert(new SysRoleMenu(1L, to.getId()));
        return count > 0;
    }

    @Override
    public List<Menu> deleteBy(Long... ids) {

        List<MenuDO> menuDOS = menuMapper.selectList(new SqlBuilder(MenuDO.class)
                .in(ID, ids)
                .build());

        menuMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(menuDOS);
    }

    @Override
    public boolean deleteRef(MenuID... menuIDS) {
        if (menuIDS.length == 0) {
            return false;
        }
        return menuMapper.deleteRef(Arrays.stream(menuIDS).map(MenuID::getValue).toArray(Long[]::new)) > 0;
    }


}
