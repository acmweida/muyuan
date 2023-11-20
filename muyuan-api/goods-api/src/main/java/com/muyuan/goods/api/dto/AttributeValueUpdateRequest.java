package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ${author}
 * @ClassName AttributeRequest
 * Description
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class AttributeValueUpdateRequest extends OptRequest implements Serializable {

    @Builder
    public AttributeValueUpdateRequest(Opt opt, Long id, String[] values) {
        super(opt);
        this.id = id;
        this.values = values;
    }

    private static final long serialVersionUID = -6515124582723029455L;

    /**
     * $column.columnComment
     */
    @NotNull
    private Long id;

    @NotEmpty(message = "values 不能为空字符串数组")
    private String[] values;

}
