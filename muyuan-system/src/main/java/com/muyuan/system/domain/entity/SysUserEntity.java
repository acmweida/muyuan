package com.muyuan.system.domain.entity;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.model.SysUserRole;
import com.muyuan.system.domain.repo.SysUserRepo;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
public class SysUserEntity extends SysUser {

    private static final String NAME_PREFIX = "my_";

    private static Random random = new Random();



    public static String createUserName() {
        StringBuffer name = new StringBuffer(NAME_PREFIX);
        name.append(StrUtil.randomString(7));
        name.append((random.nextInt(900)+100));
        return name.toString();
    }

    /**
     * 用户角色
     */
    private List<SysUserRole> sysUserRoles;

    private List<SysRole> sysRoles;

    public SysUserEntity(String username,String password) {
        super(username,password);
    }



}
