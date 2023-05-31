package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.goods.api.FeatureInterface;
import com.muyuan.goods.api.dto.FeatureDTO;
import com.muyuan.goods.api.dto.FeatureQueryRequest;
import com.muyuan.goods.api.dto.FeatureRequest;
import com.muyuan.manager.goods.dto.FeatureQueryParams;
import com.muyuan.manager.goods.dto.assembler.FeatureAssembler;
import com.muyuan.manager.goods.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 通用特征量Service业务层处理
 * 
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@Service
@Slf4j
public class FeatureServiceImpl implements FeatureService
{

    private RedisCacheService redisCacheService;

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private FeatureInterface featureInterface;

    /**
     * 查询通用特征量
     * 
     * @param id 通用特征量主键
     * @return 通用特征量
     */
    @Override
    public Optional<FeatureDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<FeatureDTO> featureHander = featureInterface.getFeature(id_);
                    return ResultUtil.getOr(featureHander, null);
                });
    }

    /**
     * 查询通用特征量列表
     *
     * @param params 通用特征量
     * @return 通用特征量
     */
    @Override
    public Page<FeatureDTO> list(FeatureQueryParams params) {
        FeatureQueryRequest request = new FeatureQueryRequest();
        request.setName(params.getName());
        request.setParentId(params.getParentId());
        request.setLeaf(params.getLeaf());

        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<FeatureDTO>> res = featureInterface.list(request);

        return res.getData();
    }


    /**
     * 新增通用特征量
     * 
     * @param request 通用特征量
     * @return 结果
     */
    @Override
    public Result add(FeatureRequest request) {
        request.setOpt(SecurityUtils.getOpt());
        return featureInterface.add(request);
    }

    /**
     * 修改通用特征量
     * 
     * @param request 通用特征量
     * @return 结果
     */
    @Override
    public Result update(FeatureRequest request) {
        request.setOpt(SecurityUtils.getOpt());
        return featureInterface.updateFeature(request);
    }

    /**
     * 批量删除通用特征量
     * 
     * @param ids 需要删除的通用特征量主键
     * @return 结果
     */
    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return featureInterface.deleteFeature(ids);
    }

    @Override
    public List<SelectTree> options(FeatureQueryParams params) {
        String key = FEATURE_KEY_PREFIX + params.getName();
        return CacheServiceUtil.getAndUpdateList(redisCacheService,key,
                () -> {
                    Page<FeatureDTO> features =list(params) ;
                    return FeatureAssembler.buildSelect(features.getRows());
                },
                SelectTree.class);
    }
}
