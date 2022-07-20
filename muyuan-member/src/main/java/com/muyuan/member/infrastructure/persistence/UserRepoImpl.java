package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.mybatis.JdbcBaseMapper;
import com.muyuan.member.domains.model.User;
import com.muyuan.member.domains.model.UserRole;
import com.muyuan.member.domains.repo.UserRepo;
import com.muyuan.member.infrastructure.persistence.mapper.UserMapper;
import com.muyuan.member.infrastructure.persistence.mapper.UserRoleMapper;
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
    public User find(int userNo) {
        return userMapper.find(userNo);
    }

    @Override
    public User selectOne(Map params) {
        return userMapper.selectOne(params);
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
        userMapper.updateBy(user, JdbcBaseMapper.ID);
    }

    @Override
    public void insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

}
