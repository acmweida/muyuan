package com.muyuan.config.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictQueryCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;
import com.muyuan.config.repo.DictRepo;
import com.muyuan.config.repo.converter.DictConverter;
import com.muyuan.config.repo.dataobject.DictDataDO;
import com.muyuan.config.repo.dataobject.DictTypeDO;
import com.muyuan.config.repo.mapper.DictDataMapper;
import com.muyuan.config.repo.mapper.DictTypeMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

/**
 * @ClassName DictDataRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 11:11
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class DictRepoImpl implements DictRepo {

    private DictDataMapper dictDataMapper;

    private DictTypeMapper dictTypeMapper;

    private DictConverter converter;

    @Override
    public List<DictData> selectByDictType(String dataType) {
        final List<DictDataDO> dictDataDOS = dictDataMapper.selectList(new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getType, dataType)
                .eq(DictDataDO::getStatus, 0)
                .orderByAsc(DictDataDO::getOrderNum));

        return converter.to(dictDataDOS);
    }

    @Override
    public Page<DictData> select(DictQueryCommand command) {
        LambdaQueryWrapper<DictDataDO> wrapper = new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getLabel, command.getLabel())
                .like(DictDataDO::getType, command.getType())
                .eq(DictDataDO::getStatus, command.getStatus())
                .orderByDesc(DictDataDO::getUpdateTime, DictDataDO::getCreateTime);

        Page<DictData> page = Page.<DictData>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<DictDataDO> list = command.enablePage() ?
                dictDataMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                dictDataMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }


    public DictType selectType(Long id) {
        DictTypeDO dictType = dictTypeMapper.selectOne(new LambdaQueryWrapper<DictTypeDO>()
                .eq(DictTypeDO::getId, id));
        return converter.to(dictType);
    }

    @Override
    public DictData selectData(Long id) {
        DictDataDO dictData = dictDataMapper.selectOne(new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getId, id));
        return converter.to(dictData);
    }

    @Override
    public Page<DictType> select(DictTypeQueryCommand command) {
        LambdaQueryWrapper<DictTypeDO> wrapper = new LambdaQueryWrapper<DictTypeDO>()
                .like(DictTypeDO::getName, command.getName())
                .like(DictTypeDO::getType, command.getType())
                .eq(DictTypeDO::getStatus, command.getStatus())
                .orderByDesc(DictTypeDO::getUpdateTime, DictTypeDO::getCreateTime);

        Page<DictType> page = Page.<DictType>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<DictTypeDO> list = command.enablePage() ?
                dictTypeMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                dictTypeMapper.selectList(wrapper);

        page.setRows(converter.toType(list));

        return page;
    }

    @Override
    public DictType selectDictType(DictType.Identify identify) {
        DictTypeDO dictType = dictTypeMapper.selectOne(new LambdaQueryWrapper<DictTypeDO>().select(DictTypeDO::getId)
                .eq(DictTypeDO::getType, identify.getType())
                .eq(DictTypeDO::getId, identify.getId()));

        return converter.to(dictType);
    }

    @Override
    public DictData selectDictData(DictData.Identify identify) {
        LambdaQueryWrapper<DictDataDO> wrapper = new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getValue, identify.getValue())
                .eq(DictDataDO::getType, identify.getType())
                .orderByDesc(DictDataDO::getUpdateTime, DictDataDO::getCreateTime);

        DictDataDO dataDO = dictDataMapper.selectOne(wrapper);

        return converter.to(dataDO);
    }

    @Override
    public boolean addDictType(DictType type) {
        Integer count = dictTypeMapper.insert(converter.to(type));
        return count > 0;
    }

    @Override
    public boolean addDictData(DictData dictData) {
        Integer count = dictDataMapper.insert(converter.to(dictData));
        return count > 0;
    }

    @Override
    public DictData updateDictDataById(DictData dictData) {

        LambdaQueryWrapper<DictDataDO> wrapper = new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getId, dictData.getId());

        DictDataDO old = dictDataMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(old)) {
            dictDataMapper.updateById(converter.to(dictData));
        }

        return converter.to(old);
    }

    @Override
    public DictType updateDictType(DictType dictType) {

        LambdaQueryWrapper<DictTypeDO> wrapper = new LambdaQueryWrapper<DictTypeDO>()
                .eq(DictTypeDO::getId, dictType.getId());

        DictTypeDO typeDO = dictTypeMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(typeDO)) {
            dictTypeMapper.updateById(converter.to(dictType));
        }

        return converter.to(typeDO);
    }

    @Override
    public boolean updateDictDataType(String oldType, String newType) {
        UpdateWrapper<DictDataDO> wrapper = new UpdateWrapper<DictDataDO>()
                .set(TYPE,newType)
                .eq(TYPE,oldType);

        return dictDataMapper.update(null,wrapper) > 0;
    }

    @Override
    public List<DictData> deleteDictData(Long... ids) {
        LambdaQueryWrapper<DictDataDO> wrapper =new LambdaQueryWrapper<DictDataDO>()
                .in(DictDataDO::getId, ids);

        List<DictDataDO> dictDataDOS = dictDataMapper.selectList(wrapper);

        dictDataMapper.delete(wrapper);

        return converter.to(dictDataDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DictType> deleteDictType(Long... ids) {

        LambdaQueryWrapper<DictTypeDO> wrapper = new LambdaQueryWrapper<DictTypeDO>()
                .in(DictTypeDO::getId, ids);
        List<DictTypeDO> dictDataDOS = dictTypeMapper.selectList(wrapper);

        String[] types = dictDataDOS.stream().map(DictTypeDO::getType).collect(Collectors.toList()).toArray(new String[0]);
        dictDataMapper.delete(new LambdaQueryWrapper<DictDataDO>().in(DictDataDO::getType, types));
        dictTypeMapper.delete(new LambdaQueryWrapper<DictTypeDO>().in(DictTypeDO::getId, ids));

        return converter.toType(dictDataDOS);
    }
}
