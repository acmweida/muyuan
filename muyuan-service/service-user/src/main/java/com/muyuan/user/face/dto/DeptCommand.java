package com.muyuan.user.face.dto;

import com.muyuan.common.bean.OptCommand;
import lombok.Data;

/**
 * @ClassName DeptParams
 * Description DeptParams
 * @Author 2456910384
 * @Date 2022/11/28 16:00
 * @Version 1.0
 */
@Data
public class DeptCommand extends OptCommand {

    private String name;

    private String status;

    private Long parentId;

    private Integer orderNum;

    private String leader;

    private String phone;

    private String email;

    private Long id;

}
