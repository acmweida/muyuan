package com.muyuan.goods.domains.enums;

import lombok.Getter;

/**
 * @ClassName BrandAuthStatus 枚举
 * Description 注意: 更新请同步修改字典数据  goods_brand_audit_status
 * @Author 2456910384
 * @Date 2022/12/19 9:39
 * @Version 1.0
 */
@Getter
public enum BrandAuthStatus {

    FAIL(2),

    AUTH(1),

    PASS(0);


    BrandAuthStatus(Integer code) {
        this.code = code;
    }

    private Integer code;

}
