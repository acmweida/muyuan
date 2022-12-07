package com.muyuan.manager.goods.dto.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryVO
 * Description 产品分类展示
 * @Author 2456910384
 * @Date 2022/6/9 15:30
 * @Version 1.0
 */
@Data
public class CategoryVO {

    private  Long id;

    /**
     * 分类编码
     */
    private Long code;

    /**
     * 父分类ID
     */
    private Long parentId;

    private String status;

    private String name;

    private String logo;

    private Integer level;

    private Integer GoodsCount;

    private String publish;

    private Integer orderNum;

    private String keywords;

    private boolean hasChildren;

    /**
     * 属性
     */
    private List<AttributeVO> attributes;
}
