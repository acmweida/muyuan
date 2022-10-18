package com.muyuan.config.service;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictQueryCommend;
import com.muyuan.config.face.dto.DictTypeCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;

import java.util.List;

/**
 * @ClassName DictDataDomainService 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:55
 * @Version 1.0
 */
public interface DictService {

    List<DictData> getByDictTypeName(DictQueryCommend commend);

    Page<DictType> list(DictTypeQueryCommand commend);

    /**
     * 检查唯一性
     * @param dictType
     * @return
     */
    String checkUnique(DictType dictType);

    boolean addDictType(DictTypeCommand command);
}
