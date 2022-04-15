package com.muyuan.system.interfaces.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName SysMenuDTO
 * Description 菜单DTO
 * @Author 2456910384
 * @Date 2022/4/15 13:55
 * @Version 1.0
 */
@Data
public class SysMenuDTO {

    private String name;

    private String status;
}
