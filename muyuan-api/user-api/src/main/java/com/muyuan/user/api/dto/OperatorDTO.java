package com.muyuan.user.api.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysUserTO
 * Description 运营人员DTO
 * @Author 2456910384
 * @Date 2022/9/13 16:18
 * @Version 1.0
 */
@Data
public class OperatorDTO {

    private Long id;
    private String username;
    private String nickName;
    private Long shopNo;
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
