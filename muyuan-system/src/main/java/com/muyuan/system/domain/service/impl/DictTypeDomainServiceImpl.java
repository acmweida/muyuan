package com.muyuan.system.domain.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.entity.DictTypeEntity;
import com.muyuan.system.domain.query.DictTypeQuery;
import com.muyuan.system.domain.service.DictTypeDomainService;
import com.muyuan.system.domain.factories.DictTypeFactory;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName DictTypeControlerImpl
 * Description 指点类型
 * @Author 2456910384
 * @Date 2022/3/31 11:43
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictTypeDomainServiceImpl implements DictTypeDomainService {

    private DictTypeQuery dictTypeQuery;

    private DictTypeRepo dictTypeRepo;


    @Override
    public Page list(DictTypeDTO dictTypeDTO) {
        return dictTypeQuery.list(dictTypeDTO);
    }

    @Override
    public int add(DictTypeDTO dictTypeDTO) {
        DictType account = dictTypeRepo.selectOne(new SqlBuilder(DictType.class).select("id")
                .eq("name", dictTypeDTO.getName())
                .or()
                .eq("type",dictTypeDTO.getType())
                .build());
        if (null != account) {
            return 1;
        }

        DictTypeEntity dictTypeEntity = DictTypeFactory.newDictTypeEntity(dictTypeDTO);
        dictTypeEntity.initInstance();
        if (dictTypeRepo.insert(dictTypeEntity)) {
            return 0;
        }
        return -1;
    }

    @Override
    public Optional<DictType> getById(String id) {
        DictType dictType = dictTypeQuery.getById(id);
        if (null == dictType) {
            return Optional.empty();
        }

        return Optional.of(dictType);
    }
}
