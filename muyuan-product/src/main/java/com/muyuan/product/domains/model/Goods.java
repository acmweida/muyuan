package com.muyuan.product.domains.model;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.redis.util.RedisCounter;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.domains.repo.GoodsRepo;
import lombok.Data;
import org.joda.time.DateTime;
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

    /**
     * 分类id
     */
    private Long categoryCode;

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

    public void save(GoodsRepo goodsRepo) {
        Assert.notNull(goodsRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> {
                            buildId();
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
        long id =  ( (now.getYear() - 2000) * 1000L +now.getDayOfYear() ) * 100000L + now.getSecondOfDay();
        this.id = (id * 1000000) + (counter.next() % 1000000);
    }

}
