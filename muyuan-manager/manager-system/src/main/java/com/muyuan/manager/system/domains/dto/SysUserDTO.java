package com.muyuan.manager.system.domains.dto;

import com.muyuan.common.bean.BaseDTO;
import com.muyuan.manager.system.domains.model.SysUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
public class SysUserDTO extends BaseDTO<SysUserDTO, SysUser> {

    @Pattern(regexp = "[a-zA-Z0-9_-]{4,16}$",message = "用户名只能由字母、数字、下划线组成，且长度是4-16位")
    @NotBlank(message = "用户名不能为空")
    private String username;

    private String phone;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private int pageSize = 10;

    private int pageNum = 1;

    @Pattern(regexp = ".*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$",
    message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符")
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    private String deptId;

    @Pattern(message = "email 格式错误",regexp = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")
    private String email;

    private Long[] roleIds;

    private String sex;

    private String remark;

    private Long id;

    private Long roleId;

}
