package com.muyuan.user.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DeptParams
 * Description DeptParams
 * @Author 2456910384
 * @Date 2022/11/28 16:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DeptRequest extends OptRequest implements Serializable {

    @Builder
    public DeptRequest(Opt opt, String name, String status, Long parentId, Integer orderNum, String leader, String phone, String email, Long id) {
        super(opt);
        this.name = name;
        this.status = status;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.leader = leader;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

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
