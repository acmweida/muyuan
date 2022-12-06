package com.muyuan.goods.domains.model.valueobject;

import com.muyuan.common.core.domains.Identifier;

/**
 * @ClassName SkuId
 * Description SKU ID
 * @Author 2456910384
 * @Date 2022/8/26 11:55
 * @Version 1.0
 */
public class SkuId implements Identifier {

    private long value;

    public SkuId(long value) {
        if ( value <= 0) {
            throw new IllegalArgumentException("SkuId must more than zero!");
        }
        this.value = value;
    }
}
