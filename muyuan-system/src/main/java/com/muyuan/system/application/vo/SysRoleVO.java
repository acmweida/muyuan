package com.muyuan.system.application.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysRoleVO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/5/9 15:39
 * @Version 1.0
 */
@Data
public class SysRoleVO {

    /**
     * 角色名称
     */
    @ExcelProperty(value = "角色名称")
    private String name;

    /**
     * 角色编码
     */
    @ExcelProperty(value = "角色编码")
    private String code;

    /**
     * 状态 0-正常 1-停用
     */
    @ExcelProperty(value = "启用状态")
    private String status;

    public SysRoleVO() {
    }
}
