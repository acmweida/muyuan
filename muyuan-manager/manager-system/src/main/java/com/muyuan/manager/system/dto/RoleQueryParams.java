package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;


/**
 * @ClassName RoleQueryParams
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@Data
public class RoleQueryParams extends PageDTO {

    private Long id;

    private String name;

    private String status;

    private Integer platformType;

}
