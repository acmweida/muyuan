package com.muyuan.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.face.dto.BrandCommand;
import com.muyuan.goods.face.dto.BrandQueryCommand;

import java.util.Optional;

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


    /**
     * 查询权限信息
     * @param id
     * @return
     */
    Optional<Brand> get(Long id);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(Brand.Identify identify);


    /**
     * 新增品牌信息
     * @param command
     * @return
     */
    boolean add(BrandCommand command);

    /**
     * 更新品牌信息
     * @param command
     * @return
     */
    boolean update(BrandCommand command);


    boolean audit(Brand brand, Integer auditStatus);

}
