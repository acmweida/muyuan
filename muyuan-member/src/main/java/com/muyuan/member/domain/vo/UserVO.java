package com.muyuan.member.domain.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserVO {

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

    private Set<String> permissions;

}
