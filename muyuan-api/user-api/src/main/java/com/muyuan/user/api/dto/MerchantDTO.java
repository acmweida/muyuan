package com.muyuan.user.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MerchantDTO
 * Description 商户DTO
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class MerchantDTO {

    private Long id;
    private String username;
    private String nickName;
    private Long shopId;
    private String password;
    private String salt;
    private String encryptKey;
    private String phone;
    private short status;
    private Date createTime;
    private Date updateTime;
    private Date lastSignTime;
    private List<String> roles;
}
