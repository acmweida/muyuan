package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.infrastructure.persistence.dao.DictDataMapper;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

       String dataDictJson =  (String)  redisCacheManager.get(RedisConst.SYS_DATA_DICT,dataType,
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
    public int insert(DictData dictData) {
        return dictDataMapper.insert(dictData);
    }

    @Override
    public int delete(String... ids) {
        return dictDataMapper.deleteByIds(ids);
    }

    @Override
    public void refreshCache(String dataDictType) {

    }

}
