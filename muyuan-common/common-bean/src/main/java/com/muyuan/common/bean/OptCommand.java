package com.muyuan.common.bean;

import com.muyuan.common.valueobject.Opt;
import lombok.Data;

/**
 * @ClassName BaseRequest
 * Description Request 基础类
 * @Author 2456910384
 * @Date 2022/12/14 15:30
 * @Version 1.0
 */
@Data
public abstract class OptCommand {

    private Opt opt;

}
