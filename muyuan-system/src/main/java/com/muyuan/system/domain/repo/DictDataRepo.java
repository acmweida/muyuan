package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.DictData;

import java.util.List;
import java.util.Map;

/**
 * 字典数据REPO
 */
public interface DictDataRepo {

    List<DictData> select(Map parms);

    List<DictData> selectByDateType(String dataType);

    DictData selectOne(Map params);

    int insert(DictData dictData);

    int delete(String... ids);

    void refreshCache(String dataDictType);
}
