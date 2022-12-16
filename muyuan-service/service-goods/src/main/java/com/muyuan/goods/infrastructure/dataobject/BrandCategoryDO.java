package com.muyuan.goods.infrastructure.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
public class BrandCategoryDO {

    /**  */
    private Long brandId;

    /**  */
    private Long categoryCode;


}
