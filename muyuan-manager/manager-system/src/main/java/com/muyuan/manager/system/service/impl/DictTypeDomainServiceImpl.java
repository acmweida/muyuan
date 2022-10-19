package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.config.api.dto.DictTypeQueryRequest;
import com.muyuan.manager.system.domains.model.DictType;
import com.muyuan.manager.system.domains.repo.DictTypeRepo;
import com.muyuan.manager.system.dto.DictTypeQueryParams;
import com.muyuan.manager.system.dto.DictTypeRequest;
import com.muyuan.manager.system.service.DictTypeDomainService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictTypeControlerImpl
 * Description 指点类型
 * @Author 2456910384
 * @Date 2022/3/31 11:43
 * @Version 1.0
 */
@Service
public class DictTypeDomainServiceImpl implements DictTypeDomainService {

    @Autowired
    private DictTypeRepo dictTypeRepo;

    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private DictInterface dictInterface;

    // ##############################  query ########################## //


    /**
     * 通过DataType 查询字典数据
     *
     * @param params
     * @return
     */
    @Override
    public Page<DictTypeDTO> list(DictTypeQueryParams params) {

        DictTypeQueryRequest request = DictTypeQueryRequest.builder()
                .name(params.getName())
                .type(params.getType())
                .status(params.getStatus())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }


        Result<Page<DictTypeDTO>> res = dictInterface.list(request);


        return res.getData();
    }

    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeRepo.select(new SqlBuilder(DictType.class).build());
    }

    /**
     * 字典类类型详情查询
     *
     * @param id
     * @return
     */
    @Override
    public Optional<DictType> getById(String id) {
        DictType dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class)
                .eq("id", id)
                .build());

        if (null == dictType) {
            return Optional.empty();
        }

        return Optional.of(dictType);

    }

    // ##############################  query ########################## //

    @Override
    public Result add(DictTypeRequest dictTypeRequest) {

        return dictInterface.addDictType(com.muyuan.config.api.dto.DictTypeRequest.builder()
                .name(dictTypeRequest.getName())
                .type(dictTypeRequest.getType())
                .status(dictTypeRequest.getStatus())
                .createBy(SecurityUtils.getUserId())
                .build());
    }


}
