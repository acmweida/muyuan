package com.muyuan.manager.system.model;

import com.muyuan.common.mybatis.jdbc.crud.ColumnExclude;
import com.muyuan.manager.system.base.common.GenConstants;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 业务表 gen_table
 * 
 */
@Data
@ColumnExclude({"treeCode","treeParentCode","treeName","parentMenuId","parentMenuName"})
public class GenTable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tableId;

    /** 表名称 */
    private String tableName;

    /** 表描述 */
    private String tableComment;

    /** 关联父表的表名 */
    private String subTableName;

    /** 本表关联父表的外键名 */
    private String subTableFkName;

    /** 实体类名称(首字母大写) */
    private String className;

    /** 使用的模板（crud单表操作 tree树表操作 sub主子表操作） */
    private String tplCategory;

    /** 生成包路径 */
    private String packageName;

    /** 生成模块名 */
    private String moduleName;

    /** 生成业务名 */
    private String businessName;

    /** 生成功能名 */
    private String functionName;

    /** 生成作者 */
    private String functionAuthor;

    /** 生成代码方式（0zip压缩包 1自定义路径） */
    private String genType;

    /** 生成路径（不填默认项目路径） */
    private String genPath;

    /** 其它生成选项 */
    private String options;

    /** 树编码字段 */
    private String treeCode;

    /** 树父编码字段 */
    private String treeParentCode;

    /** 树名称字段 */
    private String treeName;

    /** 上级菜单ID字段 */
    private String parentMenuId;

    /** 上级菜单名称字段 */
    private String parentMenuName;

    /** 创建者 */
    private String createBy;

    private String updateBy;

    private Date createTime;
    private Date updateTime;

    private String remark;

    private List<GenTableColumn> columns;

    private GenTable subTable;

    /** 主键信息 */
    private GenTableColumn pkColumn;

    public static boolean isSuperColumn(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public static boolean isSub(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }
    public boolean isTree()
    {
        return isTree(this.tplCategory);
    }

    public void init(String createBy) {
        this.createTime = new Date();
        this.createBy = createBy;
    }

    public void update(String updateBy) {
        this.updateTime = new Date();
        this.updateBy = updateBy;
    }

    public boolean isCrud()
    {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSub()
    {
        return isSub(this.tplCategory);
    }

}
