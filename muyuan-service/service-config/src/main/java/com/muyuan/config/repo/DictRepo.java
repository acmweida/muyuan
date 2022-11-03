package com.muyuan.config.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictQueryCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;

import java.util.List;

/**
 * @ClassName DictDataRepo 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:56
 * @Version 1.0
 */
public interface DictRepo {

    List<DictData> selectByDictType(String dataType);

    Page<DictData> select(DictQueryCommand commend);

    Page<DictType> select(DictTypeQueryCommand command);

    DictType selectType(Long id);

    DictData selectData(Long id);

    DictType selectDictType(DictType type);

    DictData selectDictData(DictData data);

    boolean addDictType(DictType type);

    boolean addDictData(DictData dictData);

    boolean updateDictData(DictData dictData);
}
