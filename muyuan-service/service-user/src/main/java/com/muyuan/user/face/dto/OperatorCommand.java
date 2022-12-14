package com.muyuan.user.face.dto;

import com.muyuan.common.bean.OptCommand;
import com.muyuan.user.domain.model.valueobject.RoleID;
import lombok.Data;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
public class OperatorCommand extends OptCommand {

    private String username;

    private String phone;

    private Integer status = 0;

    private String password;

    private String nickName;

    private String deptId;

    private String email;

    private RoleID[] roleIds;

    private String sex;

    private String remark;

    private Long id;

    private Long roleId;

}
