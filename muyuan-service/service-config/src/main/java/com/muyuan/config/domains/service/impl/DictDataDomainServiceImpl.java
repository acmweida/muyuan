package com.muyuan.config.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.config.domains.model.entity.DictData;
import com.muyuan.config.domains.repo.DictDataRepo;
import com.muyuan.config.domains.service.DictDataDomainService;
import com.muyuan.config.face.dto.DictQueryCommend;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
public class DictDataDomainServiceImpl implements DictDataDomainService {

    private DictDataRepo dictDataRepo;

    private RedisCacheService redisCacheService;

    @Override
    public List<DictData> getByDictTypeName(DictQueryCommend commend) {
        if (ObjectUtils.isEmpty(commend.getDictTypeName())) {
            return GlobalConst.EMPTY_LIST;
        }

        return  CacheServiceUtil.getAndUpdateList(redisCacheService, RedisConst.SYS_DATA_DICT+commend.getDictTypeName(),
                () -> dictDataRepo.selectByDictType(commend.getDictTypeName()),
                DictData.class
        );
    }
}
