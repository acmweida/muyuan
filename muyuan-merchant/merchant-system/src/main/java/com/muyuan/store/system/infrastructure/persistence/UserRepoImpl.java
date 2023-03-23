package com.muyuan.store.system.infrastructure.persistence;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.store.system.domains.model.User;
import com.muyuan.store.system.domains.model.UserRole;
import com.muyuan.store.system.domains.repo.UserRepo;
import com.muyuan.store.system.infrastructure.persistence.mapper.UserMapper;
import com.muyuan.store.system.infrastructure.persistence.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class UserRepoImpl implements UserRepo {

    private UserMapper userMapper;

    private UserRoleMapper userRoleMapper;

    @Override
    public User selectOne(User user) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, user.getId())
                .eq(User::getUsername, user.getUsername())
                .eq(User::getStatus, STATUS_OK)
                .eq(User::getPhone, user.getPhone())
        );
    }

    @Override
    public void insert(User dataObject) {
       userMapper.insert(dataObject);
    }

    @Override
    public List<User> selectAllocatedList(Map params) {
        return userMapper.selectAllocatedList(params);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public void update(User brand, String... column) {
//        userMapper.update(brand,column);
    }

}
