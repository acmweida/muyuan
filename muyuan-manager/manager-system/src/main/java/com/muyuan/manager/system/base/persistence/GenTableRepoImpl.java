package com.muyuan.manager.system.base.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.base.persistence.converter.GenTableConverter;
import com.muyuan.manager.system.base.persistence.mapper.GenTableMapper;
import com.muyuan.manager.system.dto.GenTableDTO;
import com.muyuan.manager.system.model.GenTable;
import com.muyuan.manager.system.repo.GenTableRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName GenTableRepoImpl
 * Description GenTableRepoImpl
 * @Author 2456910384
 * @Date 2022/6/1 10:34
 * @Version 1.0
 */
@AllArgsConstructor
@Component
public class GenTableRepoImpl implements GenTableRepo {

    private GenTableMapper genTableMapper;

    private GenTableConverter converter;


    @Override
    public void insert(GenTable genTable) {
        genTableMapper.insertAuto(genTable);
    }

    @Override
    public GenTable selectGenTableById(Long id) {
        return genTableMapper.selectGenTableById(id);
    }

    @Override
    public GenTable selectGenTableByName(String tableName) {
        return genTableMapper.selectGenTableByName(tableName);
    }

    @Override
    public List<GenTable> selectGenTableList(GenTableDTO genTable) {
        return genTableMapper.selectGenTableList(genTable);
    }

    @Override
    public List<GenTable> selectGenTableAll() {
        return genTableMapper.selectGenTableAll();
    }

    @Override
    public List<GenTable> selectDbTableList(GenTableDTO genTable) {
        return selectDbTableList(genTable,null);
    }

    @Override
    public List<GenTable> selectDbTableList(GenTableDTO genTable, Page page) {
       return genTableMapper.selectDbTableList(new SqlBuilder()
               .eq("tableName",genTable.getTableName())
               .eq("tableComment",genTable.getTableComment())
               .eq("beginTime",genTable.getBeginTime())
               .eq("endTime",genTable.getEndTime())
               .page(page)
               .build());
    }

    @Override
    public int updateGenTable(GenTableDTO genTableDTO) {
        return genTableMapper.updateBy(converter.to(genTableDTO),"tableId");
    }

    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    @Override
    public int deleteGenTableByIds(Long[] ids) {
        return  genTableMapper.deleteBy(new SqlBuilder()
                .in("tableId",ids)
                .build());
    }
}
