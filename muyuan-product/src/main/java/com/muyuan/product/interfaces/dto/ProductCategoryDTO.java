package com.muyuan.product.interfaces.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.product.domains.model.ProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CategoryDTO
 * Description 类目 DTO
 * @Author 2456910384
 * @Date 2022/6/9 15:35
 * @Version 1.0
 */
@Data
@ApiModel("商品分类DTO")
public class ProductCategoryDTO extends BaseDTO<ProductCategoryDTO,ProductCategory> {

    @NotBlank(message = "分类名称不能为空")
    private String name;

    private String status;

    @NotBlank(message = "分类图标不能为空")
    private String logo;

    private Long parentId;

    @NotBlank(message = "分类编码不能为空")
    private String code;

    private Integer orderNum;
}
