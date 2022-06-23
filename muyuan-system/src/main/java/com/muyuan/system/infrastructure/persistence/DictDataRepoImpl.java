package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.system.domains.model.DictData;
import com.muyuan.system.domains.repo.DictDataRepo;
import com.muyuan.system.infrastructure.persistence.mapper.DictDataMapper;
import com.muyuan.system.domains.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class DictDataRepoImpl implements DictDataRepo {

    private DictDataMapper dictDataMapper;

    private RedisCacheManager redisCacheManager;

    @Override
    public List<DictData> select(DictDataDTO dictDataDTO) {
        return  select(dictDataDTO,null);
    }

    @Override
    public List<DictData> select(DictDataDTO dictDataDTO, Page page) {
        return  dictDataMapper.selectList(new SqlBuilder(DictData.class)
                .eq("type", dictDataDTO.getType())
                .eq("status", dictDataDTO.getStatus())
                .orderByDesc("updateTime", "createTime")
                .page(page)
                .build());
    }



    @Override
    public List<DictData> selectByDataType(String dataType) {

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
        redisCacheManager.delayDoubleDel(RedisConst.SYS_DATA_DICT+dataDictType);
    }

}
