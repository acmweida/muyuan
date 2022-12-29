package com.muyuan.goods.face.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FeatureRequest
 * Description
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureCommand {

            /** $column.columnComment */
            private Long id;

            /** 属性名称 */
            private String name;

            /** $column.columnComment */
            private String parentId;

            /** 是否叶子节点 0-是 1-否 */
            private Integer leaf;

            /** 状态 */
            private Long status;

            /** $column.columnComment */
            private String creator;

            /** $column.columnComment */
            private Long createBy;

            /** $column.columnComment */
            private Date createTime;

}
