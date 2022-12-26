package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.OptCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${author}
 * @ClassName CategoryRequest
 * Description
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand extends OptCommand {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父类ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;


    /**
     * 产品编码
     */
    private Long code;

    /**
     * $column.columnComment
     */
    private String logo;

    /**
     * 0-上架 1-下架 2-删除
     */
    private Integer status;

    /**
     * $column.columnComment
     */
    private Integer orderNum;


}
