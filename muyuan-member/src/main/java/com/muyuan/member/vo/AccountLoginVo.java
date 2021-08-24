package com.muyuan.member.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AccountLoginVo {

    private String token;

    private Date expireTime;
}
