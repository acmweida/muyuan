package com.muyuan.manager.goods.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandRequest;
import com.muyuan.manager.goods.dto.BrandParams;
import com.muyuan.manager.goods.dto.BrandQueryParams;
import com.muyuan.manager.goods.model.Brand;

import java.util.List;
import java.util.Optional;

/**
 * 品牌Service接口
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
public interface BrandService
{
    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    Optional<BrandDTO> get(Long id);

    /**
     * 唯一性查询
     * @param brand
     * @return
     */
    String checkUnique(Brand brand);

    /**
     * 查询品牌列表
     *
     * @param params
     * @return 品牌集合
     */
    Page<BrandDTO> list(BrandQueryParams params);

    /**
     * 新增品牌
     * 
     * @param request 品牌
     * @return
     */
    Result add(BrandRequest request);

    /**
     * 修改品牌
     * 
     * @param brandParams 品牌
     * @return 结果
     */
    void update(BrandParams brandParams);

    /**
     * 审核品牌
     *
     * @param brandParams 品牌
     * @return 结果
     */
    void audit(BrandParams brandParams);


    /**
     * 删除品牌信息
     *
     * @param id 品牌主键
     * @return 结果
     */
    void delete(Long... id);

    /**
     * 品牌联结分类
     * @param brandParams
     */
    void linkCategory(BrandParams brandParams);

    List<Long> getBrandCategory(Long id);


    /**
     * 树形选择
     * @param brandParams
     * @return
     */
    List<SelectTree> options(BrandParams brandParams);
}
