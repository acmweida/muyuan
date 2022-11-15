package com.muyuan.manager.system.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.manager.system.dto.vo.MenuVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName MenuAssembler
 * Description MenuAssembler 菜单权限
 * @Author 2456910384
 * @Date 2022/2/15 10:21
 * @Version 1.0
 */
public class MenuAssembler {

    public static List<SelectTree> buildMenuVOSelectTree(List<MenuVO> menus) {
        List<SelectTree> selectTrees = menus.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(item -> {
            return new SelectTree(item.getId(), item.getName());
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = selectTrees.stream().collect(Collectors.toMap(SelectTree::getId, item -> item));

        for (MenuVO menu : menus) {
            long parentId = menu.getParentId();
            long id = menu.getId();
            SelectTree selectTree = new SelectTree(menu.getId(), menu.getName());
            if (parentId != 0) {
                if (treeMap.containsKey(parentId)) {
                    Object o = treeMap.get(menu.getParentId());
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
