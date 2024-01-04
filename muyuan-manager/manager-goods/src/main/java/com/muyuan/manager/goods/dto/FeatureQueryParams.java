package com.muyuan.manager.goods.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 通用特征量查询参数
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureQueryParams extends PageDTO {

    private static final long serialVersionUID = 5548139660715321650L;

    @Schema(name = "特征量名称")
    /** 属性名称 */
    private String name;

    @Schema(name = "父特征量ID")
    /** $column.columnComment */
    private String parentId;

    /** 是否叶子节点 0-是 1-否 */
    @Schema(name = "是否叶子节点 0-是 1-否")
    private Integer leaf;

    /** 状态 */
    @Schema(name = "状态 0-上架 1-下架 2-删除")
    private Integer status;

}
