package com.muyuan.user.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeptParams
 * Description DeptParams
 * @Author 2456910384
 * @Date 2022/11/28 16:00
 * @Version 1.0
 */
@Data
public class DeptRequest implements Serializable {

    private static final long serialVersionUID = 3557932248501l;

    private String name;

    private String status;

    private Long parentId;

    private Integer orderNum;

    private String leader;

    private String phone;

    private String email;

    private Long id;
}
