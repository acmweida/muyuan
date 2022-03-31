package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.infrastructure.persistence.dao.DictDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DictDataRepoImpl implements DictDataRepo {

    private DictDataMapper dictDataMapper;

    @Override
    public List<DictData> select(Map params) {
        return  dictDataMapper.selectList(params);
    }

    @Override
    public DictData selectOne(Map params) {
        return dictDataMapper.selectOne(params);
    }
}
