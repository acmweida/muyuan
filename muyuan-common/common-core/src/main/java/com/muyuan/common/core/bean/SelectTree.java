package com.muyuan.common.core.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SelectTree
 * Description 前端选择结构
 * @Author 2456910384
 * @Date 2022/4/28 14:05
 * @Version 1.0
 */
@Data
public class SelectTree {

    private Long id;

    private String label;

    private List<SelectTree> children;

    public SelectTree(Long id, String label) {
        this.id = id;
        this.label = label;
        children = new ArrayList<>();
    }

    public SelectTree() {

    }
}
