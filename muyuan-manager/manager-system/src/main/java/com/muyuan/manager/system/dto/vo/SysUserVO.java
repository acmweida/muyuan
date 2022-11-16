package com.muyuan.manager.system.dto.vo;

import com.muyuan.manager.system.model.SysRole;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SysUserVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户类型 0-个人 1-企业
     */
    private short type;

    /**
     * 店铺号
     */
    private long shopNo;

    /**
     * 图片
     */
    private String avatar;

    private List<String> roles;

    private List<SysRole> sysRoles;

    private Set<String> permissions;

}
