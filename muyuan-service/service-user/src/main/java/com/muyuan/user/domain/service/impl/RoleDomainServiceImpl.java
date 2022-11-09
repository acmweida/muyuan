package com.muyuan.user.domain.service.impl;


import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.domain.service.RoleDomainService;
import com.muyuan.user.face.dto.RoleQueryCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        return repo.selectRoleByUserId(userId,platformType);
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
}
