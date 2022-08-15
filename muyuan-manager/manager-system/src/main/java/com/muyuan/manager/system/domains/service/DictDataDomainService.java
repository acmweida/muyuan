package com.muyuan.manager.system.domains.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.domains.dto.DictDataDTO;

import java.util.List;
import java.util.Optional;

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
    Page page(DictDataDTO dictDataDTO);

    /**
     * 查询字典数据
     * @param dictDataDTO
     * @return
     */
    List<DictData> list(DictDataDTO dictDataDTO);

    Optional<DictData> get(DictDataDTO dictDataDTO);

    /**
     * 通过DataType 查询字典数据
     * @param dictType
     * @return
     */
    List<DictData> getByDataType(String dictType);


    /**
     * 字典数据添加
     * @param dictDataDTO
     */
    void add(DictDataDTO dictDataDTO);

    /**
     * 字典数据更新
     * @param dictDataDTO
     * @return
     */
    void update(DictDataDTO dictDataDTO);

    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    void deleteById(String... ids);

    /**
     * 检查唯一性
     *  类型 标签 值
     * @param dictData
     * @return
     */
    String checkUnique(DictData dictData);
}
