package com.muyuan.goods.api.to;

/**
 * @ClassName GoodsTO
 * Description 商品API 请求体
 * @Author 2456910384
 * @Date 2022/8/25 15:52
 * @Version 1.0
 */
public class GoodsTO {

    /**
     * todo:物流模板
     */
    private long wuliouModel;

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

    /**
     * 商品标签
     */
    private String tags;

}
