package com.muyuan.manager.system.domains.service.impl;

import com.muyuan.common.core.util.Convert;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.domains.model.GenTableColumn;
import com.muyuan.manager.system.domains.service.GenTableColumnService;
import com.muyuan.manager.system.infrastructure.persistence.mapper.GenTableColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务字段 服务层实现
 * 
 * @author 
 */
@Service
public class GenTableColumnServiceImpl implements GenTableColumnService
{
	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId)
	{
	    return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
	@Override
	public int insertGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.insertGenTableColumn(genTableColumn);
	}

	/**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
	@Override
	public int updateGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.updateGenTableColumn(genTableColumn);
	}

	/**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGenTableColumnByIds(String ids)
	{
		return genTableColumnMapper.deleteBy(new SqlBuilder()
				.in("id",Convert.toLongArray(ids))
				.build());
	}
}