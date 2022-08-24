package com.muyuan.goods.infrastructure.util;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.core.global.IdGenerator;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.redis.util.RedisCounter;
import org.joda.time.DateTime;

/**
 * @ClassName GoodsIdGenerater
 * Description 商品ID 生成工具
 * @Author 2456910384
 * @Date 2022/8/24 17:13
 * @Version 1.0
 */
public class GoodsIdGenerator implements IdGenerator {

    private static Counter counter = new RedisCounter("goods", ApplicationContextHandler.getContext().getBean(RedisCacheService.class));

    @Override
    public Long next() {
        DateTime now = DateTime.now();
        long id = ((now.getYear() - 2000) * 1000L + now.getDayOfYear()) * 100000L + now.getSecondOfDay();
        return (id * 1000000) + (counter.next() % 1000000);
    }
}
