package com.muyuan.product.domains.model;

import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.id.Id;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * 商品分类
 */
@Data
@Id(useGeneratedKeys = true)
public class ProductCategory {

    private Long id;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 父分类ID
     */
    private Long parentId;

    private String name;

    private String logo;

    private Integer level;

    private Integer productCount;

    /**
     * 状态
     */
    private String status;

    private Integer orderNum;

    private String ancestors;

    public void clearParentId() {
        parentId = null;
        level = 1;
        ancestors = "";
    }

    public void linkParent(ProductCategory parent) {
        Assert.notNull(id, "id can not be null!");
        FunctionUtil.of(parent)
                .ifThen(
                        () -> clearParentId(),
                        p -> {
                            parentId = p.id;
                            level = p.level + 1;
                            ancestors = StringUtils.join(p.ancestors, ",", id);
                        }
                );
    }
}
