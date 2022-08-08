package com.muyuan.product.domains.model;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.redis.util.RedisCounter;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.repo.SkuRepo;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 商品基本信息
 */
@Data
public class Goods {

    private static Counter counter = new RedisCounter("goods", ApplicationContextHandler.getContext().getBean(RedisCacheService.class));

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

    private List<Sku> skus;

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void initInstance() {
        createTime = DateTime.now().toDate();
        createBy = SecurityUtils.getUserId();
        creator = SecurityUtils.getUsername();
        shopId = SecurityUtils.getShopId();
    }


    @Transactional()
    public void save(GoodsRepo goodsRepo, SkuRepo skuRepo) {
        Assert.notNull(goodsRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> {
                            buildId();
                            if (ObjectUtils.isNotEmpty(skus)) {
                                for (Sku sku :skus) {
                                    sku.setGoodsId(id);
                                    sku.initInstance(createTime);
                                }
                                skuRepo.batchInsert(skus);
                            }
                            goodsRepo.insert(this);
                        },
                        id -> {
                            update();
                            goodsRepo.update(this);
                        }
                );
    }


    /**
     *
     */
    private void buildId() {
        DateTime now = DateTime.now();
        long id = ((now.getYear() - 2000) * 1000L + now.getDayOfYear()) * 100000L + now.getSecondOfDay();
        this.id = (id * 1000000) + (counter.next() % 1000000);
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
        sb.append(", skus=").append(skus);
        sb.append('}');
        return sb.toString();
    }
}
