package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.service.DictTypeDomainService;
import com.muyuan.system.domain.factories.DictTypeFactory;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictTypeControlerImpl
 * Description 指点类型
 * @Author 2456910384
 * @Date 2022/3/31 11:43
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictTypeDomainServiceImpl implements DictTypeDomainService {

    private DictTypeRepo dictTypeRepo;

    // ##############################  query ########################## //

    @Override
    public String checkUnique(DictType dictType) {
        Long id = null == dictType.getId() ? 0 : dictType.getId();
        dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class).select("id")
                .eq("name", dictType.getName())
                .eq("type",dictType.getType())
                .build());
        if (null != dictType && !id.equals(dictType.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
    @Override
    public Page list(DictTypeDTO dictTypeDTO) {

        SqlBuilder sqlBuilder = new SqlBuilder(DictType.class);
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getName())) {
            sqlBuilder.eq("name",dictTypeDTO.getName());
        }
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getType())) {
            sqlBuilder.eq("type",dictTypeDTO.getType());
        }
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getStatus())) {
            sqlBuilder.eq("status",dictTypeDTO.getStatus());
        }

        sqlBuilder.orderByDesc("updateTime","createTime");

        Page page = new Page();
        page.setPageNum(dictTypeDTO.getPageNum());
        page.setPageSize(dictTypeDTO.getPageSize());
        sqlBuilder.page(page);

        List<DictType> list = dictTypeRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }

    /**
     * 字典类类型详情查询
     * @param id
     * @return
     */
    @Override
    public Optional<DictType> getById(String id) {
        DictType dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class)
                .eq("id", id)
                .build());

        if (null == dictType) {
            return Optional.empty();
        }

        return Optional.of(dictType);

    }

    // ##############################  query ########################## //

    @Override
    public int add(DictTypeDTO dictTypeDTO) {
        DictType dictType = DictTypeFactory.newDictType(dictTypeDTO);
        return  dictTypeRepo.insert(dictType);
    }


}
