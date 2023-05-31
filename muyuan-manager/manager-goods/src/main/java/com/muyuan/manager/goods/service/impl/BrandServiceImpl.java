package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.goods.api.BrandInterface;
import com.muyuan.goods.api.CategoryInterface;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.api.dto.BrandRequest;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.manager.goods.dto.BrandParams;
import com.muyuan.manager.goods.dto.BrandQueryParams;
import com.muyuan.manager.goods.dto.assembler.BrandAssembler;
import com.muyuan.manager.goods.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
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

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private BrandInterface brandInterface;

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private CategoryInterface categoryInterface;

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
        BrandQueryRequest request = new BrandQueryRequest();
        request.setName(params.getName());
        request.setStatus(params.getStatus());
        request.setAuditStatus(params.getAuditStatus());

        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<BrandDTO>> res = brandInterface.list(request);

        return res.getData();
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
     * @param request 品牌
     * @return 结果
     */
    @Override
    public Result update(BrandRequest request) {
        request.setOpt(SecurityUtils.getOpt());
        return brandInterface.update(request);
    }

    /**
     * 审核品牌
     *
     * @param brandParams 品牌
     * @return 结果
     */
    @Override
    public Result audit(BrandParams brandParams) {
        return brandInterface.audit(brandParams.getId(),brandParams.getAuditStatus());
    }

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public Result delete(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }
        return brandInterface.delete(ids);
    }

    @Override
    public Result linkCategory(BrandQueryParams brandParams) {
        return brandInterface.linkCategory(brandParams.getId(),brandParams.getCategoryCodes());
    }

    @Override
    public List<Long> getBrandCategory(Long id) {
        Result<List<CategoryDTO>> result = categoryInterface.listByBrandId(id);
        if (ResultUtil.isSuccess(result)) {
            return result.getData().stream().map(CategoryDTO::getCode).collect(Collectors.toList());
        }
        return GlobalConst.EMPTY_LIST;
    }

    @Override
    public List<SelectTree> options(Long categoryCode) {
        Result<List<BrandDTO>> brands = brandInterface.listByCCategoryCode(categoryCode);
        return BrandAssembler.buildSelect(brands.getData());
    }
}
