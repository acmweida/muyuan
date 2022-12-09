package com.muyuan.user.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MerchantDTO
 * Description 商户
 * @Author 2456910384
 * @Date 2022/9/15 14:25
 * @Version 1.0
 */
@Data
public class MerchantDTO implements Serializable {

    private static final long serialVersionUID = 1457932148569l;

    private Long id;

    private String username;

    private String nickName;

    private String password;

    /**
     * 店铺号
     */
    private Long shopId;

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
}
