package com.muyuan.shop.domains.model;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.redis.util.RedisCounter;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.shop.domains.repo.ShopRepo;
import com.muyuan.shop.infrastructure.common.enums.ShopStatus;
import com.muyuan.shop.infrastructure.common.enums.ShopType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {


    private static Counter counter = new RedisCounter("shop", ApplicationContextHandler.getContext().getBean(RedisCacheService.class));

    private Long id;

    /**
     * 店铺名称
     */
    private String name;


    /**
     * 店铺标签
     */
    private String tag;

    /**
     * 类型 1-普通店铺 2-官方店铺 3-自营店铺
     */
    private Integer type;

    private Integer status;

//    /**
//     * 店铺地址
//     */
//    private long addressId;

//    /**
//     * 主营品牌
//     */
//    private String brands;
//
//    /**
//     * 支付账号信息
//     */
//    private long accountId;
//
//    /**
//     * 营业证照ID
//     */
//    private long certificateId;

    /**
     * 创建时间
     */
    private Date createTime;

    private Long createBy;

    private String creator;

    /**
     * 创建时间
     */
    private Date updateTime;

    private Long updateBy;

    private String updater;

    private void buildId() {
        DateTime now = DateTime.now();
        long id =  ( (now.getYear() - 2000) * 1000  +now.getDayOfYear() ) * 100000l + now.getSecondOfDay();
        this.id = (id * 10000) + (counter.next() % 10000);
    }


    public void initInstance(ShopType shopType) {
        Assert.isNull(id, "init must id is null");
        setStatus(ShopStatus.OK.code);
        setCreateTime(new Date());
        setType(shopType.code);
        setCreateBy(SecurityUtils.getUserId());
        setCreator(SecurityUtils.getUsername());
    }

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void save(ShopRepo shopRepo) {
        Assert.notNull(shopRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> {
                            buildId();
                            shopRepo.insert(this);
                        },
                        id -> {
                            update();
                            shopRepo.update(this);
                        }
                );
    }


}
