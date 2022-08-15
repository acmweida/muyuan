package com.muyuan.manager.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.system.domains.dto.GenTableDTO;
import com.muyuan.manager.system.domains.model.GenTable;
import com.muyuan.manager.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;


/**
 * 业务 数据层
 * 
 * @author 
 */
@Mapper
public interface GenTableMapper extends SystemBaseMapper<GenTable>
{

    @Options(useGeneratedKeys = true, keyProperty = "tableId")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(GenTable dataObject);

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTableDTO genTable);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(Map genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

}