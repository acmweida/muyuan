package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.domains.repo.AttributeRepo;
import com.muyuan.goods.domains.service.AttributeService;
import com.muyuan.goods.face.dto.AttributeCommand;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @ClassName AttributeDomainServiceImpl
 * Description 权限
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepo attributeRepo;

    @Override
    public Page<Attribute> list(AttributeQueryCommand commend) {
        return attributeRepo.select(commend);
    }

    @Override
    public String checkUnique(Attribute.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        Attribute attribute = attributeRepo.selectAttribute(identify);
        if (null != attribute && !id.equals(attribute.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean addAttribute(AttributeCommand command) {
        Attribute attribute = new Attribute();

        attribute.setId(command.getId());
        attribute.setName(command.getName());
        attribute.setCategoryCode(command.getCategoryCode());
        attribute.setType(command.getType());
        attribute.setCode(command.getCode());
        attribute.setInputType(command.getInputType());
        attribute.setHtmlType(command.getHtmlType());
        attribute.setCreateTime(DateTime.now().toDate());
        attribute.setCreateBy(command.getOpt().getId());
        attribute.setCreator(command.getOpt().getName());

        return attributeRepo.addAttribute(attribute);
    }

    @Override
    public Optional<Attribute> getAttribute(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return attributeRepo.selectAttribute(id_);
                });
    }

    @Override
    public boolean updateAttribute(AttributeCommand command) {
        Attribute attribute = new Attribute();

        attribute.setId(command.getId());
        attribute.setName(command.getName());
        attribute.setCategoryCode(command.getCategoryCode());
        attribute.setType(command.getType());
        attribute.setCode(command.getCode());
        attribute.setInputType(command.getInputType());
        attribute.setHtmlType(command.getHtmlType());
        attribute.setUpdateTime(DateTime.now().toDate());
        attribute.setUpdateBy(command.getOpt().getId());
        attribute.setUpdater(command.getOpt().getName());

        Attribute old = attributeRepo.updateAttribute(attribute);
        if (ObjectUtils.isNotEmpty(old)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAttributeById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList(Arrays.asList(ids));

        List<Attribute> olds = attributeRepo.deleteBy(removeIds.toArray(new Long[0]));

        return !olds.isEmpty();
    }


}
