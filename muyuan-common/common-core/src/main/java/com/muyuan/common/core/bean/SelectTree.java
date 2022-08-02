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

    private String value;

    private boolean leaf;

    private boolean disabled = false;

    private Long parentId;

    public SelectTree(Long id, String label) {
        this.id = id;
        this.label = label;
        children = new ArrayList<>();
    }

    public SelectTree() {

    }

    public SelectTree(String value, String label, boolean leaf) {
        this.value = value;
        this.label = label;
        this.children = new ArrayList<>();
        this.leaf = leaf;
    }

    public SelectTree(String value, String label, boolean leaf,boolean disabled) {
        this.value = value;
        this.label = label;
        this.children = new ArrayList<>();
        this.leaf = leaf;
        this.disabled = disabled;
    }

    public SelectTree(String value, String label, boolean leaf,boolean disabled,Long parentId) {
        this.value = value;
        this.label = label;
        this.children = new ArrayList<>();
        this.leaf = leaf;
        this.disabled = disabled;
        this.parentId = parentId;
    }
}
