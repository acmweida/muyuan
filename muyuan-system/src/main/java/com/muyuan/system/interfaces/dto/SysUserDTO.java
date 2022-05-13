package com.muyuan.system.interfaces.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
public class SysUserDTO {

    private String username;

    private String phone;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private int pageSize = 10;

    private int pageNum = 1;

}
