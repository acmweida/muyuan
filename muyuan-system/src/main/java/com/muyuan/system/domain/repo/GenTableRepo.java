package com.muyuan.system.domain.repo;


import com.muyuan.system.domain.model.GenTable;
import com.muyuan.system.interfaces.dto.GenTableDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GenTableRepo
 * Description GenTableRepo
 * @Author 2456910384
 * @Date 2022/6/1 9:29
 * @Version 1.0
 */
public interface GenTableRepo {

    void insert(GenTable genTable);

    GenTable selectGenTableById(Long id);
    GenTable selectGenTableByName(String tableName);

    List<GenTable> selectGenTableList(Map params);

    List<GenTable> selectGenTableAll();

    List<GenTable> selectDbTableList(GenTableDTO genTable);

    int updateGenTable(GenTableDTO genTableDTO);

    List<GenTable> selectDbTableListByNames(String[] tableNames);

    int deleteGenTableByIds(Long[] ids);


}
