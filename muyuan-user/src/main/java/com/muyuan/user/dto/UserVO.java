package com.muyuan.user.dto;

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
     * 店铺号
     */
    private Long shopId;

    /**
     * 图片
     */
    private String avatar;

    private List<String> roles;

    private Set<String> permissions;

}
