package com.muyuan.user.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class OperatorRequest extends OptRequest implements Serializable {

    @Builder
    public OperatorRequest(Opt opt, String username, String phone, Integer status, String password, String nickName, String deptId, String email, Long[] roleIds, String sex, String remark, Long id, Long roleId) {
        super(opt);
        this.username = username;
        this.phone = phone;
        this.status = status;
        this.password = password;
        this.nickName = nickName;
        this.deptId = deptId;
        this.email = email;
        this.roleIds = roleIds;
        this.sex = sex;
        this.remark = remark;
        this.id = id;
        this.roleId = roleId;
    }

    private static final long serialVersionUID = 1651932148501l;

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }


    @Pattern(regexp = "[a-zA-Z0-9_-]{4,16}$",message = "用户名只能由字母、数字、下划线组成，且长度是4-16位",groups = {Add.class,Update.class})
    @NotBlank(message = "用户名不能为空",groups = {Add.class,Update.class})
    private String username;

    @NotBlank(message = "手机号",groups = {Add.class,Update.class})
    private String phone;

    private Integer status = 0;

    @Pattern(regexp = ".*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$",
    message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符",groups = {Add.class,Update.class})
    @NotBlank(message = "密码不能为空",groups = {Add.class,Update.class})
    private String password;

    @NotBlank(message = "用户昵称不能为空",groups = {Add.class,Update.class})
    private String nickName;

    private String deptId;

    @Pattern(message = "email 格式错误",regexp = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$",groups = {Add.class,Update.class})
    private String email;

    private Long[] roleIds;

    private String sex;

    private String remark;

    @NotNull(message = "主键不能为空",groups = {Update.class})
    private Long id;

    private Long roleId;

}
