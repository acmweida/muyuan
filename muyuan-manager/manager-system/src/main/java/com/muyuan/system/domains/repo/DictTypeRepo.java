package com.muyuan.system.domains.repo;

import com.muyuan.system.domains.model.DictType;

import java.util.List;
import java.util.Map;

/**
 * 字典数据REPO
 */
public interface DictTypeRepo {

    List<DictType> select(Map dictType);

    int insert(DictType dataObject);

    DictType selectOne(Map params);
}
