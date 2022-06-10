package com.muyuan.product.domains.model;

import com.muyuan.common.mybatis.id.Id;
import lombok.Data;

/**
 * 商品分类
 */
@Data
@Id(useGeneratedKeys = true)
public class ProductCategory {

    private  Long id;

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

    private short level;

    private int ProductCount;

    private String publish;

    private int orderNum;

    private String keywords;

    private String ancestors;

}
