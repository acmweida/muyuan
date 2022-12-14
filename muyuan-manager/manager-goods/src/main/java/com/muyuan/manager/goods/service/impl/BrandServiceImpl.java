package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.goods.api.BrandInterface;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.api.dto.BrandRequest;
import com.muyuan.manager.goods.dto.BrandParams;
import com.muyuan.manager.goods.dto.BrandQueryParams;
import com.muyuan.manager.goods.dto.assembler.BrandAssembler;
import com.muyuan.manager.goods.model.Brand;
import com.muyuan.manager.goods.model.BrandCategory;
import com.muyuan.manager.goods.repo.BrandRepo;
import com.muyuan.manager.goods.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 品牌Service业务层处理
 *
 * @author 2456910384
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    private static String BRAND_KEY_PREFIX = "BRAND_OPTIONS:";

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private BrandRepo brandRepo;

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private BrandInterface brandInterface;

    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    @Override
    public Optional<BrandDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<BrandDTO> permissioHander = brandInterface.get(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    /**
     * 查询品牌列表
     *
     * @param params 品牌
     * @return 品牌
     */
    @Override
    public Page<BrandDTO> list(BrandQueryParams params) {
        BrandQueryRequest request = BrandQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .auditStatus(params.getAuditStatus())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<BrandDTO>> res = brandInterface.list(request);

        return res.getData();
    }


    @Override
    public String checkUnique(Brand brand) {
        Long id = null == brand.getId() ? 0 : brand.getId();
        brand = brandRepo.selectOne(brand);
        if (null != brand && !brand.getId().equals(id)) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 新增品牌
     *
     * @param request 品牌
     * @return 结果
     */
    @Override
    public Result add(BrandRequest request) {
        request.setOpt(SecurityUtils.getOpt());
        return brandInterface.add(request);
    }

    /**
     * 修改品牌
     *
     * @param brandParams 品牌
     * @return 结果
     */
    @Override
    public void update(BrandParams brandParams) {
        Brand brand = new Brand();
        brand.update(brandRepo);
    }

    /**
     * 审核品牌
     *
     * @param brandParams 品牌
     * @return 结果
     */
    @Override
    public void audit(BrandParams brandParams) {
        Brand brand = Brand.builder()
                .id(brandParams.getId())
                .auditStatus(brandParams.getAuditStatus())
                .build();
        brand.audit(brandRepo);
    }

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public void delete(Long... ids) {
        brandRepo.delete(ids);
    }

    @Override
    @Transactional
    public void linkCategory(BrandParams brandParams) {
        Brand brand = brandRepo.selectOne(Brand.builder()
                .id(brandParams.getId())
                .build());
        if (ObjectUtils.isEmpty(brand)) {
            return;
        }

        Long[] categoryCodes = brandParams.getCategoryCodes();
        if (ObjectUtils.isEmpty(categoryCodes)) {
            brandRepo.deleteLink(BrandCategory.builder()
                    .brandId(brand.getId())
                    .build());
        } else {
            List<BrandCategory> brandCategories = brandRepo.selectLinkCategoryCode(brand.getId());
            List<Long> codes = brandCategories.stream().map(BrandCategory::getCategoryCode).collect(Collectors.toList());
            List<Long> common = ListUtils.retainAll(Arrays.asList(categoryCodes), codes);

            // 删除
            List<BrandCategory> temp = new ArrayList<>();
            for (Long code : codes) {
                if (!common.contains(code)) {
                    temp.add(BrandCategory.builder()
                            .brandId(brand.getId())
                            .categoryCode(code)
                            .build());
                }
            }
            brandRepo.deleteLink(temp.toArray(new BrandCategory[0]));
            // 新增
            temp.clear();
            for (Long code : categoryCodes) {
                if (!common.contains(code)) {
                    temp.add(BrandCategory.builder()
                            .brandId(brand.getId())
                            .categoryCode(code)
                            .build());
                }
            }

            brandRepo.insertLink(temp);

        }

        for (Long categoryCode : brandParams.getCategoryCodes()) {
            redisCacheService.del(BRAND_KEY_PREFIX + categoryCode);
        }

    }

    @Override
    public List<Long> getBrandCategory(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return Collections.EMPTY_LIST;
        }
        return brandRepo.selectLinkCategoryCode(id).stream().map(BrandCategory::getCategoryCode).collect(Collectors.toList());
    }

    @Override
    public List<SelectTree> options(BrandParams brandParams) {

        String key = BRAND_KEY_PREFIX + brandParams.getCategoryCode();
        return CacheServiceUtil.getAndUpdateList(redisCacheService,key,
                () -> {
                    List<Brand> brands = brandRepo.selectBy(brandParams);
                    return BrandAssembler.buildSelect(brands);
                },
                SelectTree.class);
    }

}
