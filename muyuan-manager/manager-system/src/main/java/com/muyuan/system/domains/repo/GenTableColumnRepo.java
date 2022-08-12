package com.muyuan.system.domains.repo;

import com.muyuan.system.domains.model.GenTableColumn;

import java.util.List;

/**
 * @ClassName GenTableColumnRepo
 * Description 代码生成
 * @Author 2456910384
 * @Date 2022/6/1 16:30
 * @Version 1.0
 */
public interface GenTableColumnRepo {

    void insert(GenTableColumn genTableColumn);

    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    int updateGenTableColumn(GenTableColumn genTableColumn);

    int deleteGenTableColumnByIds(Long[] ids);

    int insertGenTableColumn(GenTableColumn genTableColumn);

    int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

}
