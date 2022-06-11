package com.muyuan.product.domains.vo;

/**
 * @ClassName CategoryVO
 * Description 产品分类展示
 * @Author 2456910384
 * @Date 2022/6/9 15:30
 * @Version 1.0
 */
public class ProductCategoryVO {

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
}
