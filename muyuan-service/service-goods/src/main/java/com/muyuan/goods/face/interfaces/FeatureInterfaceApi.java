package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.FeatureInterface;
import com.muyuan.goods.api.dto.FeatureDTO;
import com.muyuan.goods.api.dto.FeatureQueryRequest;
import com.muyuan.goods.api.dto.FeatureRequest;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.domains.service.FeatureService;
import com.muyuan.goods.face.dto.mapper.FeatureMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Optional;

/**
 * @ClassName FeatureInterfaceApi
 * Description 内部接口  通用特征量
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.GOODS, version = "1.0"
        , interfaceClass = FeatureInterface.class
)
public class FeatureInterfaceApi implements FeatureInterface {

    private FeatureMapper MAPPER;

    private FeatureService featureService;

    @Override
    public Result<Page<FeatureDTO>> list(FeatureQueryRequest request) {
        Page<Feature> list = featureService.list(MAPPER.toCommand(request));

        return ResultUtil.success( Page.copy(list,MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result add(FeatureRequest request) {
        if (featureService.exists(new Feature.Identify(request.getId()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = featureService.addFeature(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<FeatureDTO> getFeature(Long id) {
        Optional<Feature> handler = featureService.getFeature(id);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateFeature(FeatureRequest request) {
        if (featureService.exists(new Feature.Identify(request.getId()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = featureService.updateFeature(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteFeature(Long... ids) {
        if (featureService.deleteFeatureById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
