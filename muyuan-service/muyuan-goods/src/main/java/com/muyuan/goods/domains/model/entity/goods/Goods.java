package com.muyuan.goods.domains.model.entity.goods;

import com.muyuan.common.core.domains.mode.entity.Entity;
import com.muyuan.common.core.global.IdGenerator;
import com.muyuan.common.web.util.SecurityUtils;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * 商品基本信息
 */
@Data
public class Goods implements Entity<GoodsId> {

    private GoodsId id;


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


    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更吓人ID
     */
    private Long updateBy;

    /**
     * 创建人ID
     */
    private Long createBy;

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void newInstance(IdGenerator idGenerator) {
        createTime = DateTime.now().toDate();
        createBy = SecurityUtils.getUserId();
        creator = SecurityUtils.getUsername();
        shopId = SecurityUtils.getShopId();
        setId(new GoodsId(idGenerator.next()));
    }

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
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updater='").append(updater).append('\'');
        sb.append(", creator='").append(creator).append('\'');
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createBy=").append(createBy);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public GoodsId getId() {
        return id;
    }

    public void setId(GoodsId goodsId) {
        this.id = goodsId;
    }
}
