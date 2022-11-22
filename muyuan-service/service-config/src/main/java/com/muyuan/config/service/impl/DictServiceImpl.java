package com.muyuan.config.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictDataCommand;
import com.muyuan.config.face.dto.DictQueryCommand;
import com.muyuan.config.face.dto.DictTypeCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;
import com.muyuan.config.repo.DictRepo;
import com.muyuan.config.service.DictService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName DictDataDomainServiceImpl
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 11:02
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictServiceImpl implements DictService {

    private DictRepo dictRepo;

    private RedisCacheService redisCacheService;

    @Override
    public List<DictData> getByDictTypeName(DictQueryCommand commend) {
        if (ObjectUtils.isEmpty(commend.getType())) {
            return GlobalConst.EMPTY_LIST;
        }

        return CacheServiceUtil.getAndUpdateList(redisCacheService, RedisConst.SYS_DATA_DICT + commend.getType(),
                () -> dictRepo.selectByDictType(commend.getType()),
                DictData.class
        );
    }

    @Override
    public Page<DictType> list(DictTypeQueryCommand commend) {
        return dictRepo.select(commend);
    }

    @Override
    public Optional<DictType> getType(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return dictRepo.selectType(id_);
                });
    }

    @Override
    public Optional<DictData> getData(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return dictRepo.selectData(id_);
                });
    }

    @Override
    public Page<DictData> list(DictQueryCommand commend) {
        return dictRepo.select(commend);
    }

    @Override
    public String checkUnique(DictType.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId();
        DictType dictType = dictRepo.selectDictType(identify);
        if (null != dictType && !id.equals(dictType.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public String checkUnique(DictData.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId();
        DictData dictData = dictRepo.selectDictData(identify);
        if (null != dictData && !id.equals(dictData.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean addDictData(DictDataCommand command) {
        DictData dictData = new DictData();

        dictData.setType(command.getType());
        dictData.setLabel(command.getLabel());
        dictData.setOrderNum(command.getOrderNum());
        dictData.setValue(command.getValue());
        dictData.setCssClass(command.getCssClass());
        dictData.setListClass(command.getListClass());
        dictData.setRemark(command.getRemark());
        dictData.setStatus(Integer.parseInt(command.getStatus()));
        dictData.setCreateTime(DateTime.now().toDate());
        dictData.setCreateBy(command.getCreateBy());

        if (dictRepo.addDictData(dictData)) {
            redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+dictData.getType());
            return true;
        }

        return  false;
    }

    @Override
    public boolean updateDictData(DictDataCommand command) {
        if (ObjectUtils.isEmpty(command.getId())) {
            return false;
        }

        DictData dictData = new DictData();

        dictData.setId(command.getId());
        dictData.setType(command.getType());
        dictData.setLabel(command.getLabel());
        dictData.setOrderNum(command.getOrderNum());
        dictData.setValue(command.getValue());
        dictData.setCssClass(command.getCssClass());
        dictData.setListClass(command.getListClass());
        dictData.setRemark(command.getRemark());
        dictData.setStatus(Integer.parseInt(command.getStatus()));
        dictData.setUpdateTime(DateTime.now().toDate());
        dictData.setUpdateBy(command.getUpdateBy());

        DictData old = dictRepo.updateDictData(dictData);
        if (ObjectUtils.isNotEmpty(old)) {
            redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+old.getType());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictType(DictTypeCommand command) {
        if (ObjectUtils.isEmpty(command.getId())) {
            return false;
        }

        DictType dictType = new DictType();

        dictType.setId(command.getId());
        dictType.setType(command.getType());
        dictType.setName(command.getName());
        dictType.setRemark(command.getRemark());
        dictType.setStatus(command.getStatus());
        dictType.setUpdateTime(DateTime.now().toDate());
        dictType.setUpdateBy(command.getUpdateBy());

        DictType old = dictRepo.updateDictType(dictType);
        if (ObjectUtils.isNotEmpty(old) ) {
            if (!command.getType().equals(dictType.getType())) {
            redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+old.getType());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDictData(Long... ids) {
        List<DictData> old = dictRepo.deleteDictData(ids);
        for (String type :  old.stream().map(DictData::getType).collect(Collectors.toList())) {
            redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+type);
        }

        return !old.isEmpty();
    }

    @Override
    public boolean deleteDictType(Long... ids) {
        List<DictType> old = dictRepo.deleteDictType(ids);
        for (String type :  old.stream().map(DictType::getType).collect(Collectors.toList())) {
            redisCacheService.delayDoubleDel(RedisConst.SYS_DATA_DICT+type);
        }

        return !old.isEmpty();
    }

    @Override
    public boolean addDictType(DictTypeCommand command) {
        DictType dictType = new DictType();

        dictType.setName(command.getName());
        dictType.setType(command.getType());
        dictType.setStatus(command.getStatus());
        dictType.setRemark(command.getRemark());
        dictType.setCreateBy(command.getCreateBy());
        dictType.setCreateTime(DateTime.now().toDate());

        return dictRepo.addDictType(dictType);
    }


}
