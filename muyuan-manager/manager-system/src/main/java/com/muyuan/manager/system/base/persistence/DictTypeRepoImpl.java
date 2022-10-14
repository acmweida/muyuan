package com.muyuan.manager.system.base.persistence;

import com.muyuan.manager.system.domains.model.DictType;
import com.muyuan.manager.system.domains.repo.DictTypeRepo;
import com.muyuan.manager.system.mapper.DictTypeMapper;
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
        return dictTypeMapper.insertAuto(dictType);
    }

    @Override
    public DictType selectOne(Map params) {
        return dictTypeMapper.selectOne(params);
    }
}
