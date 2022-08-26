package com.muyuan.goods.domains.model.entity.goods;

import com.muyuan.common.core.domains.Identifier;
import lombok.Data;

/**
 * @ClassName GoodsId
 * Description 商品ID
 * @Author 2456910384
 * @Date 2022/8/26 10:32
 * @Version 1.0
 */
@Data
public class GoodsId implements Identifier {

    private Long value;

    public GoodsId(long value) {
        if ( value <= 0) {
            throw new IllegalArgumentException("GoodsId must more than zero!");
        }
        this.value = value;
    }

}
