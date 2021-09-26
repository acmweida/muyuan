package com.muyuan.goods.domains.model;

import lombok.Data;

/**
 * 类目品牌关联
 */
@Data
public class CategoryBrandRel {

    private long id;

    private long categoryId;

    private long brandId;

    private boolean isDelete;

}
