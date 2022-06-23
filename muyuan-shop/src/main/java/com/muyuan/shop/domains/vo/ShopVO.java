package com.muyuan.shop.domains.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "店铺信息")
public class ShopVO {

    private long id;

    /**
     * 店铺名称
     */
    @ApiModelProperty(name = "店铺名称")
    private String name;


    /**
     * 商店编码
     */
    @ApiModelProperty(name = "商店编码")
    private String shopNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    private Date createTime;

    /**
     * 店铺标签
     */
    @ApiModelProperty(name = "店铺标签")
    private String tag;

    /**
     * 类型 1-普通店铺 2-官方店铺 3-自营店铺
     */
    @ApiModelProperty(name = "店铺标签")
    private String type;

    /**
     * 店铺地址
     */
    @ApiModelProperty(name = "店铺地址")
    private long addressId;

    /**
     * 主营品牌
     */
    @ApiModelProperty(name = "主营品牌")
    private String brands;

}
