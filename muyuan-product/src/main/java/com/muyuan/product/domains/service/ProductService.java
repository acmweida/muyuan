package com.muyuan.product.domains.service;

import java.util.Optional;

/**
 * @ClassName ProductService
 * Description 商品域服务接口
 * @Author 2456910384
 * @Date 2021/10/13 15:27
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 添加商品
     * @return
     */
    Optional<Long> addProduct();
}
