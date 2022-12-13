package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import com.muyuan.goods.infrastructure.converter.BrandConverter;
import com.muyuan.goods.infrastructure.dataobject.BrandDO;
import com.muyuan.goods.infrastructure.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.goods.infrastructure.mapper.BrandMapper.*;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Component
@AllArgsConstructor
public class BrandRepoImpl implements BrandRepo {

    private BrandMapper brandMapper;

    private BrandConverter converter;

    @Override
    public Brand select(Long id) {
        BrandDO brandDO = brandMapper.selectOne(new SqlBuilder(BrandDO.class)
                .eq(ID, id)
                .build());
        return converter.to(brandDO);
    }

    @Override
    public Page<Brand> select(BrandQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(BrandDO.class)
                .like(NAME, command.getName())
                .eq(STATUS, command.getStatus())
                .eq(AUDIT_STATUS, command.getAuditStatus())
                .eq(DEL, GlobalConst.FALSE);

        Page<Brand> page = Page.<Brand>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<BrandDO> list = brandMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }
}
