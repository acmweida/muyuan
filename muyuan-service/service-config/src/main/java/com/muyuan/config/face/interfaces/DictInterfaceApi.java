package com.muyuan.config.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictQueryRequest;
import com.muyuan.config.domains.service.DictDataDomainService;
import com.muyuan.config.face.dto.mapper.DictMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @ClassName DictInterfaceApi
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 9:58
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.CONFIG, version = "1.0"
        , interfaceClass = DictInterface.class
)
public class DictInterfaceApi implements DictInterface {

    private DictDataDomainService dictDataDomainService;

    private DictMapper mapper;

    @Override
    public Result<List<DictDataDTO>> getDictDataByType(DictQueryRequest request) {
        return ResultUtil.success(
                mapper.to(dictDataDomainService.getByDictTypeName(mapper.toCommend(request)))
        );
    }
}
