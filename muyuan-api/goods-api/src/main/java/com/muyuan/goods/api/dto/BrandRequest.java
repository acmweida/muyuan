package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BrandRequest extends OptRequest implements Serializable {

    private static final long serialVersionUID = 6384577570342029546L;

    @Builder
    public BrandRequest(Opt opt, Long id, String name, String logo, Integer orderNum, String englishName, String remark, String status) {
        super(opt);
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.orderNum = orderNum;
        this.englishName = englishName;
        this.remark = remark;
        this.status = status;
    }

    /**
     *
     */
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 图标
     */
    private String logo;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 英文名称
     */
    private String englishName;

    private String status;

    /**
     * 备注
     */
    private String remark;

}
