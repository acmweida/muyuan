package com.muyuan.auth.dto;

import lombok.Data;

@Data
public class ImageCaptchaLoginParams {

    private String captcha;

    private String uuid;

    private String platformType;

    private String username;

    private String password;

}
