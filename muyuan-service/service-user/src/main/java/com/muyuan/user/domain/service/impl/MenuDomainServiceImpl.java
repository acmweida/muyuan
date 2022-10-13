package com.muyuan.user.domain.service.impl;

import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.MenuQueryCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuDomainServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 14:55
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class MenuDomainServiceImpl implements MenuDomainService {

    private MenuRepo menuRepo;

    @Override
    public List<Menu> getMenuByRoleCodes(MenuQueryCommand request) {
        RoleCode[] roleCodes = request.getRoleCodes();
        List<Menu> menus = new ArrayList<>();
        for (RoleCode roleCode : roleCodes) {
            menus.addAll(menuRepo.selectByRoleCode(roleCode,request.getPlatformType()));
        }
        return menus;
    }
}
