package com.muyuan.system.domain.model;

import com.muyuan.common.mybatis.id.AutoIncrement;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Role
 * Description 角色 t_role
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
public class SysRole {

    @AutoIncrement
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String sort;

    /**
     * 状态 0-正常 1-停用
     */
    private String status;

    private Long createBy;

    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public SysRole() {
    }

    public SysRole(String code) {
        this.code = code;
    }

    public SysRole(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
