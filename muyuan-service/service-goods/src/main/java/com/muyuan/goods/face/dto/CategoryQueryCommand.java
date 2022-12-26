package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${author}
 * @ClassName CategoryQueryRequest
 * Description
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQueryCommand extends PageDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父类ID
     */
    private Long parentId;

    private Long[] parentIds;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 晨级
     */
    private Integer level;

    /**
     * 产品编码
     */
    private Long code;

    /**
     * 层级路径
     */
    private String ancestors;

    /**
     * $column.columnComment
     */
    private Long productCount;

    /**
     * 0-上架 1-下架 2-删除
     */
    private Long status;

    /**
     * 是否叶子节点
     */
    private String leaf;

}
