package com.muyuan.common.bean;

import com.muyuan.common.valueobject.Opt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BaseRequest
 * Description Request 基础类
 * @Author 2456910384
 * @Date 2022/12/14 15:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptRequest  implements Serializable {

    private static final long serialVersionUID = -2629423392479260199L;

    private Opt opt;

}
