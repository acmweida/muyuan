package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.entity.Permission;
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
    public List<Menu> selectByPermissions(List<Permission> permissions, PlatformType platformType) {
        Long[] menuIds = permissions.stream().filter(item -> item.getType().equals(GlobalConst.TYPE_DIR) || item.getType().equals(GlobalConst.TYPE_MENU))
                .map(Permission::getResourceRef).toArray(Long[]::new);
        if (menuIds.length == 0) {
            return GlobalConst.EMPTY_LIST;
        }
        List<MenuDO> menuDOS = menuMapper.selectByPermissions(menuIds, platformType.getCode());
        return converter.to(menuDOS);
    }

    @Override
    public List<Menu> list(MenuQueryCommand command) {
        List<MenuDO> menuDOS = menuMapper.selectList(new LambdaQueryWrapper<MenuDO>()
                .like(ObjectUtils.isNotEmpty(command.getName()), MenuDO::getName, command.getName())
                .eq(MenuDO::getStatus, command.getStatus())
                .in(ObjectUtils.isNotEmpty(command.getParentIds()), MenuDO::getParentId, command.getParentIds())
                .eq(MenuDO::getPlatformType, ObjectUtils.isEmpty(command.getPlatformType()) ? null : command.getPlatformType().getCode())
                .orderByAsc(MenuDO::getOrderNum));

        return converter.to(menuDOS);
    }

    @Override
    public Menu selectMenu(MenuID id) {
        MenuDO menuDO = menuMapper.selectOne(new LambdaQueryWrapper<MenuDO>()
                .eq(MenuDO::getId, id.getValue()));
        return converter.to(menuDO);
    }

    @Override
    public Menu selectMenu(Menu.Identify identify) {
        MenuDO menuDO = menuMapper.selectOne(new LambdaQueryWrapper<MenuDO>()
                .select(MenuDO::getId)
                .eq(MenuDO::getName, identify.getName())
                .eq(MenuDO::getParentId, identify.getParentId())
                .eq(ObjectUtils.isEmpty(identify.getId()), MenuDO::getId, identify.getId().getValue())
                .eq(MenuDO::getPlatformType, identify.getPlatformType().getCode()));

        return converter.to(menuDO);
    }

    @Override
    public Menu updateDMenu(Menu menu) {

        MenuDO menuDO = menuMapper.selectById(menu.getId().getValue());
        if (ObjectUtils.isNotEmpty(menuDO)) {
            menuMapper.updateById(converter.to(menu));
        }

        return converter.to(menuDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(Menu menu) {
        MenuDO to = converter.to(menu);
        Integer count = menuMapper.insert(to);
        menu.setId(new MenuID(to.getId()));

        // 默认管理员权限
//        sysRoleMenuMapper.insert(new SysRoleMenu(1L, to.getId()));
        return count > 0;
    }

    @Override
    public List<Menu> deleteBy(Long... ids) {

        List<MenuDO> menuDOS = menuMapper.selectList(new LambdaQueryWrapper<MenuDO>()
                .in(MenuDO::getId, ids));

        menuMapper.deleteById(ids);

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
