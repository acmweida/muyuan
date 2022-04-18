package com.muyuan.system.domain.query;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.repo.SysUserRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;
/**
 * @ClassName MenuQuery
 * Description 菜单查询
 * @Author 2456910384
 * @Date 2022/2/9 16:21
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class SysUserQuery {

    private SysUserRepo userRepo;

    /**
     * 通过UserNO 获取用户信息
     * @param sysUser
     * @return
     */
    public Optional<SysUser> get(SysUser sysUser) {
        Assert.isTrue(sysUser != null,"sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(SysUser.class);
        if (ObjectUtils.isNotEmpty(sysUser.getId())) {
            sqlBuilder.eq("id", sysUser.getId());
        }
        if (ObjectUtils.isNotEmpty(sysUser.getUsername())) {
            sqlBuilder.eq("username", sysUser.getUsername());
        }
        sqlBuilder.eq("status",0);

        final SysUser user = userRepo.selectOne(sqlBuilder.build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
