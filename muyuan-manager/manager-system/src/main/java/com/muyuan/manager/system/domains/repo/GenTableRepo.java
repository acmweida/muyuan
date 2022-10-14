package com.muyuan.manager.system.domains.repo;


import com.muyuan.common.bean.Page;
import com.muyuan.manager.system.dto.GenTableDTO;
import com.muyuan.manager.system.domains.model.GenTable;

import java.util.List;

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

    List<GenTable> selectGenTableList(GenTableDTO genTable);

    List<GenTable> selectGenTableAll();

    List<GenTable> selectDbTableList(GenTableDTO genTable, Page page);

    List<GenTable> selectDbTableList(GenTableDTO genTable);

    int updateGenTable(GenTableDTO genTableDTO);

    List<GenTable> selectDbTableListByNames(String[] tableNames);

    int deleteGenTableByIds(Long[] ids);


}
