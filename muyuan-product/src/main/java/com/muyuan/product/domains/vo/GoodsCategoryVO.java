package com.muyuan.product.domains.vo;

import lombok.Data;

/**
 * @ClassName CategoryVO
 * Description 产品分类展示
 * @Author 2456910384
 * @Date 2022/6/9 15:30
 * @Version 1.0
 */
@Data
public class GoodsCategoryVO {

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

    private Integer level;

    private Integer ProductCount;

    private String publish;

    private Integer orderNum;

    private String keywords;

    private boolean hasChildren;
}
