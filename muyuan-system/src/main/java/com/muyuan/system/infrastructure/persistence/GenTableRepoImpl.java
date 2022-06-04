package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domain.model.GenTable;
import com.muyuan.system.domain.repo.GenTableRepo;
import com.muyuan.system.infrastructure.persistence.dao.GenTableMapper;
import com.muyuan.system.interfaces.dto.GenTableDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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


    @Override
    public void insert(GenTable genTable) {
        genTableMapper.insertGenTable(genTable);
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
    public List<GenTable> selectGenTableList(Map params) {
        return genTableMapper.selectList(params);
    }

    @Override
    public List<GenTable> selectGenTableAll() {
        return genTableMapper.selectGenTableAll();
    }

    @Override
    public List<GenTable> selectDbTableList(GenTableDTO genTable) {
        return genTableMapper.selectDbTableList(genTable.convert());
    }

    @Override
    public int updateGenTable(GenTableDTO genTableDTO) {
        return genTableMapper.updateGenTable(genTableDTO.convert());
    }

    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    @Override
    public int deleteGenTableByIds(Long[] ids) {
        return genTableMapper.deleteGenTableByIds(ids);
    }
}
