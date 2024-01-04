package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName ConfigParams
 * Description 参数配置实体
 * @Author wd
 * @Date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Schema(name = "参数配置")
@Data
public class ConfigParams {

    /**
     * 校验分组
     */
    public interface Add {

    }

    public interface Update {

    }

    /**
     * 参数主键
     */
    @Schema(name = "id")
    @NotNull(message = "参数主键不能为空", groups = {Update.class})
    private Long id;
    /**
     * 参数名称
     */
    @Schema(name = "name")
    @NotBlank(message = "参数名称不能为空", groups = {Add.class, Update.class})
    private String name;
    /**
     * 参数键名
     */
    @Schema(name = "configKey")
    @NotBlank(message = "参数键名不能为空", groups = {Add.class, Update.class})
    private String configKey;
    /**
     * 参数键值
     */
    @Schema(name = "configValue")
    @NotBlank(message = "参数键值不能为空", groups = {Add.class, Update.class})
    private String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    @Schema(name = "type")
    @NotNull(message = "系统内置（Y是 N否）不能为空", groups = {Add.class, Update.class})
    private Integer type;
    /**
     * 创建者
     */
    @Schema(name = "createBy")
    @NotBlank(message = "创建者不能为空", groups = {Add.class, Update.class})
    private String createBy;

    /**
     * 备注
     */
    @Schema(name = "remark")
    @NotBlank(message = "备注不能为空", groups = {Add.class, Update.class})
    private String remark;


}
