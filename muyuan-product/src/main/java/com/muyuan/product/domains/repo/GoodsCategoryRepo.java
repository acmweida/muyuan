package com.muyuan.product.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.product.domains.model.BrandCategory;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;

import java.util.List;

/**
 * @ClassName ProductCategoryRepo 接口
 * Description 商品分类 Repository
 * @Author 2456910384
 * @Date 2022/6/10 11:37
 * @Version 1.0
 */
public interface GoodsCategoryRepo extends BaseRepo {

    String LEVEL = "level";

    String NAME = "name";

    String CODE = "code";

    String LEAF = "leaf";

    String STATUS_DELETE = "2";

    Object[] STATUS_OK = new String[]{"0","1"};

    List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO);

    List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO, String... column);

    GoodsCategory selectOne(GoodsCategory goodsCategory);

    void insert(GoodsCategory goodsCategory);

    void update(GoodsCategory goodsCategory);

    void update(GoodsCategory goodsCategory, String... column);

    /**
     * Count 兄弟节点
     *
     * @param goodsCategoryDTO
     * @return
     */
    int count(GoodsCategoryDTO goodsCategoryDTO);

    /**
     * Count 兄弟节点 包括被删除节点
     *
     * @param goodsCategoryDTO
     * @return
     */
    int countAll(GoodsCategoryDTO goodsCategoryDTO);

    void delete(Long[] ids);

    List<BrandCategory> selectBrand(Long... categoryCode);

}
