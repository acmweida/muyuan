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
    public String checkUnique(DictType dictType) {
        Long id = null == dictType.getId() ? 0 : dictType.getId();
        dictType = dictRepo.selectDictType(dictType);
        if (null != dictType && !id.equals(dictType.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public String checkUnique(DictData dictData) {
        Long id = null == dictData.getId() ? 0 : dictData.getId();
        dictData = dictRepo.selectDictData(dictData);
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

        return dictRepo.addDictData(dictData);
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
