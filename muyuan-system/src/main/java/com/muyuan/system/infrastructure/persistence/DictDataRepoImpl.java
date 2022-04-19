package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.infrastructure.persistence.dao.DictDataMapper;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
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

    @Override
    public int insert(DictData dictData) {
        return dictDataMapper.insert(dictData);
    }

    @Override
    public boolean delete(String... ids) {
        Integer rows = dictDataMapper.deleteByIds(ids);
        return rows > 0;
    }
}
