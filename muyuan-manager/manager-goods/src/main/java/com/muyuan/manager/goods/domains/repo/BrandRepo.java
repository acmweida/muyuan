package com.muyuan.manager.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.manager.goods.domains.dto.BrandDTO;
import com.muyuan.manager.goods.domains.model.Brand;
import com.muyuan.manager.goods.domains.model.BrandCategory;

import java.util.List;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */

public interface BrandRepo  extends BaseRepo {

    String AUDIT_STATUS = "auditStatus";

    String DEL = "del";

    List<Brand> select(BrandDTO brandDTO);

    List<Brand> select(BrandDTO brandDTO, Page page);

    Brand selectOne(Brand brand);

    void insert(Brand brand);

    void update(Brand brand);

    void update(Brand brand, String... column);

    void delete(Long... ids);

    List<BrandCategory> selectLinkCategoryCode(Long brandId);

    void deleteLink(BrandCategory... brandCategory);

    void insertLink(List<BrandCategory> brandCategories);

    List<Brand> selectBy(BrandDTO brandDTO);

}
