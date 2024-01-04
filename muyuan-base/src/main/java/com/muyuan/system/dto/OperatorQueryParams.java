package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "用户信息查询")
public class OperatorQueryParams extends PageDTO {

    @Serial
    private static final long serialVersionUID = -2773702793063693870L;

    public interface SelectAllow {

    }

    @Schema(name = "角色名称")
    @NotNull(message = "角色ID不能为空",groups = SelectAllow.class)
    private Long roleId;

    @Schema(name = "用户名称")
    private String username;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "状态")
    private String status;

    @Schema(name = "平台类型")
    private String platformType;

}
