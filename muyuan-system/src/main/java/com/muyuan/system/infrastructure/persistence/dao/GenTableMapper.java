package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.GenTable;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import com.muyuan.system.interfaces.dto.GenTableDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 业务 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface GenTableMapper extends SystemBaseMapper<GenTable>
{
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

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    int insertGenTable(GenTable genTable);


}