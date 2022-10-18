package com.muyuan.user.face.dto;

import lombok.Data;

@Data
public class RegisterCommand {

    private String username;

    private String password;

    private String phone;

    private String code;

    private String uuid;

}
