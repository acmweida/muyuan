package com.muyuan.config.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictQueryCommend;
import com.muyuan.config.face.dto.DictTypeCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;
import com.muyuan.config.repo.DictRepo;
import com.muyuan.config.service.DictService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<DictData> getByDictTypeName(DictQueryCommend commend) {
        if (ObjectUtils.isEmpty(commend.getDictTypeName())) {
            return GlobalConst.EMPTY_LIST;
        }

        return  CacheServiceUtil.getAndUpdateList(redisCacheService, RedisConst.SYS_DATA_DICT+commend.getDictTypeName(),
                () -> dictRepo.selectByDictType(commend.getDictTypeName()),
                DictData.class
        );
    }

    @Override
    public Page<DictType> list(DictTypeQueryCommand commend) {
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
