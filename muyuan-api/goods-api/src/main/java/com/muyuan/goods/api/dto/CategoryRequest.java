package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ${author}
 * @ClassName CategoryRequest
 * Description
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest extends OptRequest implements Serializable {

    private static final long serialVersionUID = 1357932148568l;

    @Builder
    public CategoryRequest(Opt opt, Long id, Long parentId, String name, Long level, Long code, String ancestors, String logo, Long status, Long orderNum, String leaf, Long subCount) {
        super(opt);
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.level = level;
        this.code = code;
        this.ancestors = ancestors;
        this.logo = logo;
        this.status = status;
        this.orderNum = orderNum;
        this.leaf = leaf;
        this.subCount = subCount;
    }

    /**
     * 主键
     */
    private Long id;

    /**
     * 父类ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 晨级
     */
    private Long level;

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
    private String logo;

    /**
     * 0-上架 1-下架 2-删除
     */
    private Long status;

    /**
     * $column.columnComment
     */
    private Long orderNum;

    /**
     * 是否叶子节点
     */
    private String leaf;

    /**
     * 子节点数量
     */
    private Long subCount;

}
