package com.muyuan.system.domain.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DictDataService
 * Description 字典数据 Service
 * @Author 2456910384
 * @Date 2022/4/7 10:36
 * @Version 1.0
 */
public interface DictDataDomainService {

    /**
     * 查询字典数据
     * @param dictDataDTO
     * @return
     */
    Page list(DictDataDTO dictDataDTO);

    /**
     * 通过DataType 查询字典数据
     * @param dictType
     * @return
     */
    List<DictData> getByDataType(String dictType);

    /**
     * 字典数据添加
     * @param dictDataDTO
     * @return 0-成功 1-已存在 2-失败
     */
    Integer add(DictDataDTO dictDataDTO);

    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    int deleteById(String... ids);

    /**
     * 检查唯一性
     *  类型 标签 值
     * @param dictData
     * @return
     */
    String checkUnique(DictData dictData);
}
