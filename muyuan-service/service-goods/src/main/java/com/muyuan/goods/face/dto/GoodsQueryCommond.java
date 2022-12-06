package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;

/**
 * @ClassName GoodsQueryRequest
 * Description 商品查询请求
 * @Author 2456910384
 * @Date 2022/8/26 14:38
 * @Version 1.0
 */
@Data
public class GoodsQueryCommond extends PageDTO {

    /**
     * 商品标题
     */
    private String name;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long categoryCode;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 是否上架
     */
    private String status;

}
