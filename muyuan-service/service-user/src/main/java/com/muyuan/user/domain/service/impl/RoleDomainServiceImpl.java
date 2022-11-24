package com.muyuan.user.domain.service.impl;


import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.domain.service.RoleDomainService;
import com.muyuan.user.face.dto.RoleCommand;
import com.muyuan.user.face.dto.RoleQueryCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色域服务接口
 */
@Service
@AllArgsConstructor
@Slf4j
public class RoleDomainServiceImpl implements RoleDomainService {

    private RoleRepo repo;

    @Override
    public List<Role> selectRoleByUserId(UserID userId, PlatformType platformType) {

        return repo.selectRoleByUserId(userId, platformType);
    }

    @Override
    public Page<Role> list(RoleQueryCommand command) {
        Page<Role> select = repo.select(command);

        return select;
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return repo.select(id_);
                });
    }

    @Override
    public String checkUnique(Role.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId().getValue();
        Role role = repo.selectRole(identify);
        if (null != role && !id.equals(role.getId().getValue())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(RoleCommand command) {
        Role role = new Role();

        role.setPlatformType(command.getPlatformType());
        role.setName(command.getName());
        role.setCode(command.getCode());
        role.setOrderNum(command.getOrderNum());
        role.setStatus(command.getStatus());
        role.setCreateBy(command.getCreateBy());
        role.setCreateTime(DateTime.now().toDate());

        repo.addRole(role);
        List<MenuID> menuIDS = new ArrayList<>();
        for (Long menuId : command.getPermissionIds()) {
            menuIDS.add(new MenuID(menuId));
        }

        return  repo.addRef(role.getId(), menuIDS.toArray(new MenuID[0]));
    }
}
