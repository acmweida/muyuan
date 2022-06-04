package com.muyuan.system.interfaces.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.system.domain.model.GenTable;
import com.muyuan.system.domain.model.GenTableColumn;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GenTableDTO
 * Description 代码生成  表信息DTO
 * @Author 2456910384
 * @Date 2022/5/31 17:18
 * @Version 1.0
 */
@Data
public class GenTableDTO extends BaseDTO<GenTableDTO, GenTable> {

    private String tableName;

    private String tableComment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String options;

    private Map params;

    private List<GenTableColumn> columns;

    private String subTableName;

    /** 使用的模板（crud单表操作 tree树表操作 sub主子表操作） */
    private String tplCategory;

    /** 本表关联父表的外键名 */
    private String subTableFkName;
}

