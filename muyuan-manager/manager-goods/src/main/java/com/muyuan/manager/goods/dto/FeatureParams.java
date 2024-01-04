package com.muyuan.manager.goods.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName FeatureParams
 * Description 通用特征量实体
 * @Author ${author}
 * @Date 2022-12-29T15:39:22.465+08:00
 * @Version 1.0
 */
@Schema(name = "通用特征量")
@Data
public class FeatureParams {

    /**
     * 校验分组
     */
    public interface Add {

    }

    public interface Update {

    }

    /**
     * $column.columnComment
     */
    @Schema(name = "id")
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;
    /**
     * 属性名称
     */
    @Schema(name = "name")
    @NotBlank(message = "属性名称不能为空", groups = {Add.class, Update.class})
    private String name;
    /**
     * HTML元素类型
     */
    @Schema(name = "htmlType")
    @NotNull(message = "HTML元素类型不能为空", groups = {Add.class, Update.class})
    private Long htmlType;
    /**
     * 父属性ID
     */
    @Schema(name = "parentId")
    @NotNull(message = "父属性ID不能为空", groups = {Add.class, Update.class})
    private Long parentId;
    /**
     * 状态
     */
    @Schema(name = "status")
    @NotNull(message = "状态不能为空", groups = {Add.class, Update.class})
    private Long status;

    /**
     * 属性编码
     */
    @Schema(name = "code")
    @NotBlank(message = "属性编码不能为空", groups = {Add.class, Update.class})
    private String code;

}
