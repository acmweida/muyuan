package com.muyuan.user.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDTO
 * Description 用户公共抽象类
 * @Author 2456910384
 * @Date 2022/12/12 15:30
 * @Version 1.0
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1457932148569l;

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

    private List<RoleDTO> roles;

    private Integer sex;

    private String email;

    private Long shopId;
}
