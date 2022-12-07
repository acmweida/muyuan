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


//
//    String DEL = "del";
//
//    List<Brand> select(BrandCommand brandParams);
//
//    List<Brand> select(BrandCommand brandParams, Page page);
//
//    Brand selectOne(Brand brand);

    Page<Brand> select(BrandQueryCommand command);

//    void insert(Brand brand);
//
//    void update(Brand brand);
//
//    void update(Brand brand, String... column);

//    void delete(Long... ids);

//    List<BrandCategory> selectLinkCategoryCode(Long brandId);
//
//    void deleteLink(BrandCategory... brandCategory);
//
//    void insertLink(List<BrandCategory> brandCategories);
//
//    List<Brand> selectBy(BrandParams brandParams);

}
