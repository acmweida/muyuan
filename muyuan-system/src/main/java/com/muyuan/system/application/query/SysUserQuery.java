package com.muyuan.system.application.query;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.repo.SysUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @ClassName MenuQuery
 * Description 菜单查询
 * @Author 2456910384
 * @Date 2022/2/9 16:21
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class SysUserQuery {

    private SysUserRepo userRepo;


    /**
     * 通过UserNO 获取用户信息
     * @param userId
     * @return
     */
    public Optional<SysUser> getUserInfo(Long userId) {
        final SysUser user = userRepo.selectOne(new SqlBuilder(SysUser.class)
                .eq("id", userId)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    /**
     * 通过Uaccount 获取用户信息
     * @param username
     * @return
     */
    public Optional<SysUser> getUserByUsername(String username) {
        final SysUser user = userRepo.selectOne(new SqlBuilder(SysUser.class)
                .eq("username", username)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
