package com.muyuan.user.face.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MenuQueryCommand
 * Description MenuQueryCommand
 * @Author 2456910384
 * @Date 2022/9/16 11:37
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptQueryCommand {

    private String name;

    private String status;
}
