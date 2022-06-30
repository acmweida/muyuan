package com.muyuan.product.infrastructure.common.constant;

/**
 * @ClassName Consts 接口
 * Description 常量
 * @Author 2456910384
 * @Date 2022/6/30 15:01
 * @Version 1.0
 */
public interface Const {
    /** 公共属性 */
    int CATEGORY_ATTRIBUTE_COMMON = 1;

    /** 销售属性 */
    int CATEGORY_ATTRIBUTE_SALE = 1 << 1;

    /** 关键属性 */
    int CATEGORY_ATTRIBUTE_KEY = 1 << 2;

    /** 其他属性 */
    int CATEGORY_ATTRIBUTE_NORMAL = 1 << 3;
}
