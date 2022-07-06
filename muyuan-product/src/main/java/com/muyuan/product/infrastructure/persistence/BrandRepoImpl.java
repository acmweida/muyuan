package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.BrandDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.repo.BrandRepo;
import com.muyuan.product.infrastructure.persistence.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Brand> select(BrandDTO brandDTO) {
        return select(brandDTO, null);
    }

    @Override
    public List<Brand> select(BrandDTO brandDTO, Page page) {
        return brandMapper.selectList(new SqlBuilder(Brand.class)
                .like("name", brandDTO.getName())
                .eq("status", brandDTO.getStatus())
                .eq("auditStatus", brandDTO.getAuditStatus())
                .eq("del",GlobalConst.FALSE)
                .page(page)
                .build());
    }

    @Override
    public Brand selectOne(Brand brandDTO) {
        return brandMapper.selectOne(new SqlBuilder(Brand.class)
                .eq("id", brandDTO.getId())
                .eq("name", brandDTO.getName())
                .build());
    }

    @Override
    public void insert(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateBy(brand, "id");
    }

    @Override
    public void update(Brand brand, String... column) {
        brandMapper.updateColumnBy(brand, column, "id");
    }

    @Override
    public void delete(Long... ids) {
        brandMapper.update(new SqlBuilder(Brand.class)
                .set("del", GlobalConst.TRUE)
                .in("id", ids)
                .build());
    }

}
