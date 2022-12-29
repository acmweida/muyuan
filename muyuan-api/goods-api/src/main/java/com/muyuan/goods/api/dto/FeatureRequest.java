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
 * @ClassName FeatureRequest
 * Description
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureRequest extends OptRequest implements Serializable {

    private static final long serialVersionUID = 1357932148568l;

    @Builder
    public FeatureRequest(Opt opt, Long id, String name, String parentId, Integer leaf, Long status) {
        super(opt);
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.leaf = leaf;
        this.status = status;
    }

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * $column.columnComment
     */
    private String parentId;

    /**
     * 是否叶子节点 0-是 1-否
     */
    private Integer leaf;

    /**
     * 状态
     */
    private Long status;

}
