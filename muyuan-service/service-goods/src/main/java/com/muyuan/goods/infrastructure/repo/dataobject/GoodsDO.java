package com.muyuan.goods.infrastructure.repo.dataobject;

import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;

/**
 * 商品基本信息
 */
@Data
public class GoodsDO extends BaseDO {

//    /**
//     * todo:物流模板
//     */
//    private Long wuliouModel;

    /**
     * 商品标题
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 删除 0-否 1-是
     */
    private String status;

    /**
     * 品牌ID
     */
    private Long brandId;

    private String brandName;

    /**
     * 分类id
     */
    private Long categoryCode;

    private String categoryName;

    /**
     * 库存
     */
    private Integer stock;

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


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Goods{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", sales=").append(sales);
        sb.append(", status='").append(status).append('\'');
        sb.append(", brandId=").append(brandId);
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", categoryCode=").append(categoryCode);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", stock=").append(stock);
        sb.append(", shopId=").append(shopId);
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", tags='").append(tags).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
