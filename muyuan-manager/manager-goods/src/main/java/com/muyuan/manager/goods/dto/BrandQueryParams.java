package com.muyuan.manager.goods.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


/**
 *
 * @author wd
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandQueryParams extends PageDTO {


    public interface  Link {

    }

    @Schema(name = "以通过审核的品牌ID")
    @NotNull(message = "品牌主建不能为空",groups = {Link.class})
    private Long id;

    /** 品牌名称 */
    private String name;

    /** 审核状态  1-审核中  0-审核通过 2-审核魏通过 */
    @Schema(name = "审核状态  1-审核中  0-审核通过 2-审核未通过")
    private Integer auditStatus;

    /** 状态  0-上架 1-下架 3-删除 4-禁用 */
    @Range(message = "状态码输入错误",min = 0,max = 4)
    @Schema(name = " 状态  0-上架 1-下架 3-删除 4-禁用")
    private Integer status;

    @Schema(name = "类目Code列表")
    @Size(min = 1,groups = {Link.class},message = "类目Code不能为空")
    private Long[] categoryCodes;

}
