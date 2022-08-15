package com.muyuan.manager.product.domains.model;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.redis.util.RedisCounter;
import com.muyuan.manager.product.domains.repo.SkuRepo;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;

@Data
public class Sku {

    private static Counter counter = new RedisCounter("sku", ApplicationContextHandler.getContext().getBean(RedisCacheService.class));

    private Long id;

    private Long goodsId;

    /**
     * 售价
     */
    private Double price;

    private String pic;

    /**
     * 锁定库存
     */
    private Integer stockLock;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 库存
     */
    private Integer stock;

    private String context;

    private Date createTime;

    private Date updateTime;

    public void initInstance() {
        initInstance(DateTime.now().toDate());
    }

    public void initInstance(Date createTime) {
        this.createTime = createTime;
        this.stockLock = 0;
        this.buildId();
    }

    private void update() {
        updateTime = DateTime.now().toDate();
    }

    public void save(SkuRepo skuRepo) {
        Assert.notNull(skuRepo, "repo is null");
        Assert.notNull(id, "id is null");
        update();
        skuRepo.update(this);
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
