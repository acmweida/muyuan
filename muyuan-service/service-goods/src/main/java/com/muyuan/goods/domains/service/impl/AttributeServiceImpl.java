package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.exception.ResourceNotFoundException;
import com.muyuan.goods.api.dto.AttributeValueUpdateRequest;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.domains.model.entity.AttributeValue;
import com.muyuan.goods.domains.repo.AttributeRepo;
import com.muyuan.goods.domains.repo.AttributeValueRepo;
import com.muyuan.goods.domains.service.AttributeService;
import com.muyuan.goods.face.dto.AttributeCommand;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
import com.muyuan.goods.face.dto.AttributeValueQueryCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ${author}
 * @ClassName AttributeDomainServiceImpl
 * Description 权限
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepo attributeRepo;

    private AttributeValueRepo attributeValueRepo;

    @Override
    public Page<Attribute> page(AttributeQueryCommand command) {
        return attributeRepo.select(command);
    }

    @Override
    public boolean exists(Attribute.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        Attribute attribute = attributeRepo.selectAttribute(identify);
        if (null != attribute && !id.equals(attribute.getId())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAttribute(AttributeCommand command) {
        Attribute attribute = new Attribute();

        attribute.setName(command.getName());
        attribute.setCategoryCode(command.getCategoryCode());
        attribute.setType(command.getType());
        attribute.setCode(command.getCode());
        attribute.setInputType(command.getInputType());
        attribute.setHtmlType(command.getHtmlType());
        attribute.setCreateTime(DateTime.now().toDate());
        attribute.setCreateBy(command.getOpt().getId());
        attribute.setCreator(command.getOpt().getName());

        boolean b = attributeRepo.addAttribute(attribute);
        List<AttributeValue> attributeValues = new ArrayList<>();
        for (String value : command.getValues()) {
            AttributeValue temp = new AttributeValue();
            temp.setAttributeId(attribute.getId());
            temp.setValue(value);
            attributeValues.add(temp);
        }

        return attributeValueRepo.batchInsert(attributeValues);
    }

    @Override
    public Optional<Attribute> getAttribute(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Attribute attribute = attributeRepo.selectAttribute(id_);
                    if (attribute.getInputType() == 2 && attribute.getValueType() == 1) {
                        AttributeValueQueryCommand command = new AttributeValueQueryCommand();
                        command.setAttributeId(id);
                        List<AttributeValue> valus = attributeValueRepo.select(command).getRows();
                        attribute.setAttributeValues(valus);
                    }
                    return attribute;
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
    public boolean updateValues(AttributeValueUpdateRequest request) {
        Optional<Attribute> handle = getAttribute(request.getId());
        if (!handle.isPresent()) {
            log.info("attribute values update fail, attribute not found");
            throw new ResourceNotFoundException("attribute not found");
        }

        Attribute attribute = handle.get();
        // 取值类型校验
        if (attribute.getInputType() == 2 && attribute.getValueType() == 1) {
            List<AttributeValue> attributeValues = attribute.getAttributeValues();
            List<String> newValues = Arrays.stream(request.getValues()).map(String::trim).collect(Collectors.toList());
            List<AttributeValue> toDel = new ArrayList<>();
            for (AttributeValue value : attributeValues) {
                if (newValues.contains(value.getValue())) {
                    newValues.remove(value.getValue());
                } else {
                    toDel.add(value);
                }
            }

            // 更新取值
            if (!toDel.isEmpty()) {
                attributeValueRepo.deleteBy(toDel.stream().map(AttributeValue::getId).toArray(Long[]::new));
            }
            if (!newValues.isEmpty()) {
                attributeValueRepo.batchInsert(newValues.stream().map(item -> new AttributeValue(attribute.getId(), item)).collect(Collectors.toList()));
            }
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
