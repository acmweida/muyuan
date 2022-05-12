package com.muyuan.common.core.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SelectValue
 * Description 已选信息
 * @Author 2456910384
 * @Date 2022/5/5 15:20
 * @Version 1.0
 */
@Data
public class SelectValue {

    private List checkedKeys;

    private List<SelectTree> selectTree;

    public SelectValue() {
    }

    public SelectValue(List checkedKeys,List<SelectTree> selectTree) {
        this.checkedKeys = checkedKeys;
        this.selectTree = selectTree;
    }
}
