package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.DictData;

import java.util.List;
import java.util.Map;

/**
 * 字典数据REPO
 */
public interface DictDataRepo {

    List<DictData> select(Map params);

    DictData selectOne(Map params);

    boolean insert(DictData dictData);

    boolean delete(String... ids);
}
