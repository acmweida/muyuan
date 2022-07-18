package com.muyuan.product.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


/**
 * 品牌分类关联对象 t_brand_category
 *
 * @author ${author}
 * @date 2022-07-04T14:32:53.214+08:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandCategory {

    /**  */
    private Long brandId;

    /**  */
    private Long categoryCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandCategory that = (BrandCategory) o;
        return Objects.equals(brandId, that.brandId) && Objects.equals(categoryCode, that.categoryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, categoryCode);
    }
}
