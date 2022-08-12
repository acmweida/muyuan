package com.muyuan.system.domains.repo;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domains.model.DictData;
import com.muyuan.system.domains.dto.DictDataDTO;

import java.util.List;

/**
 * 字典数据REPO
 */
public interface DictDataRepo {

    List<DictData> select(DictDataDTO dictDataDTO);

    List<DictData> select(DictDataDTO dictDataDTO, Page page);

    List<DictData> selectByDataType(String dataType);

    DictData selectOne(DictData dictData);

    void insert(DictData dictData);

    void update(DictData dictData);

    void delete(String... ids);

    void refreshCache(String dataDictType);
}
