package com.store.system.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.store.system.domains.model.User;
import com.store.system.domains.model.UserRole;
import com.store.system.domains.repo.UserRepo;
import com.store.system.infrastructure.persistence.mapper.UserMapper;
import com.store.system.infrastructure.persistence.mapper.UserRoleMapper;
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
        return userMapper.selectOne(new SqlBuilder(User.class)
                .eq(UserRepo.ID, user.getId())
                .eq(UserRepo.USERNAME, user.getUsername())
                .eq(UserRepo.STATUS, STATUS_OK)
                .eq(UserRepo.PHONE, user.getPhone())
                .build()
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
        userMapper.updateBy(user, ID);
    }

    @Override
    public void insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public void update(User brand, String... column) {
        userMapper.updateBy(brand,column);
    }

}
