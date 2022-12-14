package com.muyuan.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.face.dto.BrandQueryCommand;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */

public interface BrandRepo {

    Brand select(Long id);

    Page<Brand> select(BrandQueryCommand command);

    Brand select(Brand.Identify identify);

    boolean add(Brand permission);

}
