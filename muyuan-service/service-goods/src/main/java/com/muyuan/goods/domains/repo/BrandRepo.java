package com.muyuan.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.enums.BrandAuthStatus;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.face.dto.BrandQueryCommand;

import java.util.List;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */

public interface BrandRepo {

    Brand select(Long id, BrandAuthStatus... authStatuses);

    List<Brand> selectByCategoryCode(Long... categoryCodes);

    Page<Brand> select(BrandQueryCommand command);

    Brand select(Brand.Identify identify);

    boolean add(Brand permission);

    /**
     * 更新信息
     * @param brand
     * @return old value
     */
    boolean update(Brand brand);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Brand> deleteBy(Long... ids);


    /**
     * 品牌分类关联
     * @param brandIds
     * @return
     */
    void deleteRef(List<Long> brandIds);

    /**
     * 品牌分类关联
     * @param brandId
     * @param categoryCode
     * @return
     */
    void deleteRef(Long brandId,Long... categoryCode);

    /**
     * 品牌分类关联
     * @param brandId
     * @param categoryCode
     */
    void addRef(Long brandId,Long... categoryCode);

}
