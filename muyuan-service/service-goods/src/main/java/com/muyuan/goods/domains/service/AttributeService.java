package com.muyuan.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.api.dto.AttributeValueUpdateRequest;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.face.dto.AttributeCommand;
import com.muyuan.goods.face.dto.AttributeQueryCommand;

import java.util.Optional;

/**
 * @ClassName AttributeDomainService 接口
 * Description 类目属性接口
 * @author ${author}
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
public interface AttributeService {

    /**
     * 类目属性分页查询
     * @param command
     * @return
     */
    Page<Attribute> page(AttributeQueryCommand command);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    boolean exists(Attribute.Identify identify);

    /**
     * 新增类目属性信息
     * @param command
     * @return
     */
    boolean addAttribute(AttributeCommand command);

    /**
     * 查询类目属性信息
     * @param id
     * @return
     */
    Optional<Attribute> getAttribute(Long id);

    /**
     * 更新类目属性信息
     * @param command
     * @return
     */
    boolean updateAttribute(AttributeCommand command);

    /**
     * 更新类目属性信息
     * @param request
     * @return
     */
    boolean updateValues(AttributeValueUpdateRequest request);

    /**
     * 删除类目属性信息
     * @param ids
     * @return
     */
    boolean deleteAttributeById(Long... ids);
}
