package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.infrastructure.persistence.dao.DictDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class DictDataRepoImpl implements DictDataRepo {

    private DictDataMapper dictDataMapper;

    private RedisCacheManager redisCacheManager;

    @Override
    public List<DictData> select(Map params) {
        return  dictDataMapper.selectList(params);
    }

    @Override
    public List<DictData> selectByDateType(String dataType) {

        String dataDictJson = redisCacheManager.getAndUpdate(RedisConst.SYS_DATA_DICT+dataType,
                () ->
                        JSONUtil.toJsonString(
                                dictDataMapper.selectList( new SqlBuilder(DictData.class)
                                        .eq("type", dataType)
                                        .eq("status", 0)
                                        .build())
                        )
                );

        return new ArrayList<>(JSONUtil.parseObjectList(dataDictJson, ArrayList.class, DictData.class));
    }

    @Override
    public DictData selectOne(Map params) {
        return dictDataMapper.selectOne(params);
    }

    @Override
    public void insert(DictData dictData) {
        dictDataMapper.insert(dictData);
        refreshCache(dictData.getType());
    }

    @Override
    public void delete(String... ids) {
        List<DictData> id = dictDataMapper.selectList(new SqlBuilder(DictData.class)
                .in("id", ids)
                .build());
        Set<String> typeSet = new HashSet<>();
        id.forEach(item -> {
            typeSet.add(item.getType());
        });

        dictDataMapper.deleteBy(new SqlBuilder().in("id",ids).build());

        typeSet.forEach(type -> {
            refreshCache(type);
        });
    }

    @Override
    public void refreshCache(String dataDictType) {
        redisCacheManager.delayDoubleDel(RedisConst.SYS_DATA_DICT+dataDictType);
    }

}
