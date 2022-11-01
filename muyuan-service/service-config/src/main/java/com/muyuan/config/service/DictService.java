package com.muyuan.config.service;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictDataCommand;
import com.muyuan.config.face.dto.DictQueryCommand;
import com.muyuan.config.face.dto.DictTypeCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictDataDomainService 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:55
 * @Version 1.0
 */
public interface DictService {

    List<DictData> getByDictTypeName(DictQueryCommand commend);

    Page<DictType> list(DictTypeQueryCommand commend);

    Optional<DictType> getType(Long id);

    Optional<DictData> getData(Long id);

    Page<DictData> list(DictQueryCommand commend);

    /**
     * 检查唯一性
     *
     * @param dictType
     * @return
     */
    String checkUnique(DictType dictType);

    /**
     * 检查唯一性
     *
     * @param dictData
     * @return
     */
    String checkUnique(DictData dictData);


    boolean addDictType(DictTypeCommand command);


    boolean addDictData(DictDataCommand command);
}
