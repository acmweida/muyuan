package com.muyuan.manager.system.base.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.manager.system.dto.DictDataRequest;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.domains.repo.DictDataRepo;
import com.muyuan.manager.system.mapper.DictDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DictDataRepoImpl implements DictDataRepo {

    private DictDataMapper dictDataMapper;

    private RedisCacheService redisCacheService;

    @Override
    public List<DictData> select(DictDataRequest dictDataRequest) {
        return  select(dictDataRequest,null);
    }

    @Override
    public List<DictData> select(DictDataRequest dictDataRequest, Page page) {
        return  dictDataMapper.selectList(new SqlBuilder(DictData.class)
                .eq("type", dictDataRequest.getType())
                .eq("status", dictDataRequest.getStatus())
                .orderByDesc("updateTime", "createTime")
                .page(page)
                .build());
    }



    @Override
    public DictData selectOne(DictData dictData) {
        return dictDataMapper.selectOne(new SqlBuilder(DictData.class)
                .eq("id", dictData.getId())
                .eq("status", dictData.getStatus())
                .eq("type", dictData.getType())
                .eq("label", dictData.getLabel())
                .eq("value", dictData.getValue())
                .build());
    }

    @Override
    public void insert(DictData dictData) {
        dictDataMapper.insertAuto(dictData);
        refreshCache(dictData.getType());
    }

    @Override
    public void update(DictData dictData) {
        dictDataMapper.updateBy(dictData,"id");
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
        redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+dataDictType);
    }

}
