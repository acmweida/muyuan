package com.muyuan.user.api.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDTO
 * Description UserDTO
 * @Author 2456910384
 * @Date 2022/9/15 14:25
 * @Version 1.0
 */
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String nickName;

    private String password;

    private String salt;

    private String encryptKey;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Date lastSignTime;

    private List<String> roles;

    private String type;
}
