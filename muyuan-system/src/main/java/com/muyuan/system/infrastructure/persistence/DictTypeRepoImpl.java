package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.infrastructure.persistence.dao.DictTypeMapper;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DictTypeRepoImpl implements DictTypeRepo {

    private DictTypeMapper dictTypeMapper;

    @Override
    public List<DictType> select(Map params) {
        return  dictTypeMapper.selectList(params);
    }

    @Override
    public int insert(DictType dictType) {
        return dictTypeMapper.insert(dictType);
    }

    @Override
    public DictType selectOne(Map params) {
        return dictTypeMapper.selectOne(params);
    }
}
