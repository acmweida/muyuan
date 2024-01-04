package com.muyuan.system.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.user.api.dto.DeptDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName SysDeptAssembler
 * Description 部门试图转换
 * @Author 2456910384
 * @Date 2022/5/13 11:48
 * @Version 1.0
 */
public class DeptAssembler {

    public static List<SelectTree> buildDeptSelectTree(List<DeptDTO> depts) {
        List<SelectTree> selectTrees = depts.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(item -> {
            return new SelectTree(item.getId(), item.getName());
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = selectTrees.stream().collect(Collectors.toMap(SelectTree::getId, item -> item));

        for (DeptDTO dept : depts) {
            long parentId = dept.getParentId();
            long id = dept.getId();
            SelectTree selectTree = new SelectTree(dept.getId(),dept.getName());
            if (parentId != 0) {
                if (treeMap.containsKey(parentId)) {
                    Object o = treeMap.get(dept.getParentId());
                    // 父节点 parentId 遍历过 但父节点没有遍历  保存该节点到当前字子节点列表
                    if (o instanceof Collection) {
                        ((Collection<SelectTree>) o).add(selectTree);
                    } else
                        // 如果父节点已经遍历过 且加入其子节点
                        if (o instanceof SelectTree) {
                            ((SelectTree) o).getChildren().add(selectTree);
                        }
                } else {
                    // 父节点 parentId 没有遍历过 先创建保存当前节点到parentId的子节点列表
                    List<SelectTree> childrens = new ArrayList<>();
                    childrens.add(selectTree);
                    treeMap.put(parentId, childrens);
                }
                // 当前接口已存在 而节点没有遍历 这次节点为非叶子节点 直接设置当前节点的字节点
                if (treeMap.containsKey(id)) {
                    selectTree.setChildren((ArrayList<SelectTree>) treeMap.get(id));
                }
                treeMap.put(id, selectTree);
            }
        }

        return selectTrees;
    }
}
