package com.muyuan.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.face.dto.BrandQueryCommand;

/**
 * @ClassName BrandService
 * Description 品牌基础信息
 * @Author 2456910384
 * @Date 2022/12/7 16:13
 * @Version 1.0
 */
public interface BrandService {

    /**
     * 品牌分页查询
     * @param commend
     * @return
     */
    Page<Brand> list(BrandQueryCommand commend);
}
