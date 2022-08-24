package com.muyuan.goods.domains.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("商品返回体")
public class GoodsVO {

    private Long id;

//    /**
//     * todo:物流模板
//     */
//    private Long wuliouModel;

    /**
     * 商品标题
     */
    private String name;

    /**
     * 删除 0-否 1-是
     */
    private String status;

    /**
     * 品牌ID
     */
    private Long brandId;

    private String brandName;

    private Integer stock;

    /**
     * 分类id
     */
    private Long categoryCode;

    private String categoryName;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 主图
     */
    private String picture;

    /**
     * 商品标签
     */
    private String tags;

    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 更新人 */
    private String updater;

    /** 创建人 */
    private String creator;

    /** 更吓人ID */
    private Long updateBy;

    /** 创建人ID */
    private Long createBy;


}
