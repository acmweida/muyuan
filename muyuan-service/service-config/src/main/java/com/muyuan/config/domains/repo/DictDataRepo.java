package com.muyuan.config.domains.repo;

import com.muyuan.config.domains.model.entity.DictData;

import java.util.List;

/**
 * @ClassName DictDataRepo 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:56
 * @Version 1.0
 */
public interface DictDataRepo {

    List<DictData> selectByDictType(String dataType);
}
