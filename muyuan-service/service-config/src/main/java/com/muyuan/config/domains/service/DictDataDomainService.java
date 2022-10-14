package com.muyuan.config.domains.service;

import com.muyuan.config.domains.model.entity.DictData;
import com.muyuan.config.face.dto.DictQueryCommend;

import java.util.List;

/**
 * @ClassName DictDataDomainService 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:55
 * @Version 1.0
 */
public interface DictDataDomainService {

    List<DictData> getByDictTypeName(DictQueryCommend commend);
}
