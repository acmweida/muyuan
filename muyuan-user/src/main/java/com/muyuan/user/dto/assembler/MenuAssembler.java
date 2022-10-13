package com.muyuan.user.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.dto.MenuVO;
import com.muyuan.user.dto.MetaVO;
import com.muyuan.user.dto.RouterVO;
import com.muyuan.user.dto.converter.MenuConverter;
import com.muyuan.user.dto.converter.MenuConverterImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MenuAssembler
 * Description MenuAssembler 菜单权限
 * @Author 2456910384
 * @Date 2022/2/15 10:21
 * @Version 1.0
 */
public class MenuAssembler {

    private static final MenuConverter converter = new MenuConverterImpl();

    public static List<SelectTree> buildMenuSelectTree(List<MenuDTO> menus) {
        List<SelectTree> selectTrees = menus.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(item -> {
            return new SelectTree(item.getId(), item.getName());
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = selectTrees.stream().collect(Collectors.toMap(SelectTree::getId, item -> item));

        for (MenuDTO menu : menus) {
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

    /**
     * 构建目录树结构
     *
     * @param menus
     * @return
     */
    public static List<MenuVO> buildMenuTree(List<MenuDTO> menus) {
        List<MenuVO> roots = menus.stream().filter(item -> item.getParentId() == 0).map(item ->
                converter.toVO(item)).collect(Collectors.toList());

        Map<Long, Object> treeMap = roots.stream().collect(Collectors.toMap(MenuVO::getId, item -> item));

        for (MenuDTO menu : menus) {
            Long parentId = menu.getParentId();
            Long id = menu.getId();
            MenuVO menuVO = converter.toVO(menu);
            if (parentId != 0) {
                if (treeMap.containsKey(parentId)) {
                    Object o = treeMap.get(menu.getParentId());
                    // 父节点 parentId 遍历过 但父节点没有遍历  保存该节点到当前字子节点列表
                    if (o instanceof Collection) {
                        ((Collection<MenuVO>) o).add(menuVO);
                    } else
                        // 如果父节点已经遍历过 且加入其子节点
                        if (o instanceof MenuVO) {
                            ((MenuVO) o).getChildren().add(menuVO);
                        }
                } else {
                    // 父节点 parentId 没有遍历过 先创建保存当前节点到parentId的子节点列表
                    List<MenuVO> childrens = new ArrayList<>();
                    childrens.add(menuVO);
                    treeMap.put(parentId, childrens);
                }
                // 当前接口已存在 而节点没有遍历 这次节点为非叶子节点 直接设置当前节点的字节点
                if (treeMap.containsKey(id)) {
                    menuVO.setChildren((ArrayList<MenuVO>) treeMap.get(id));
                }
                treeMap.put(id, menuVO);
            }
        }

        return roots;
    }


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public static List<RouterVO> buildMenus(List<MenuVO> menus) {
        List<RouterVO> routers = new LinkedList<RouterVO>();

        for (MenuVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVO(menu.getName(), menu.getIcon(), 1 == menu.getCache(), menu.getPath()));
            List<MenuVO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && GlobalConst.TYPE_DIR.equals(menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVO(menu.getName(), menu.getIcon(), 0 == menu.getCache(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new MetaVO(menu.getName(), menu.getIcon()));
                router.setPath("/inner");
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(GlobalConst.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVO(menu.getName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public static String getRouteName(MenuVO menu) {
        String routerName = StrUtil.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StrUtil.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isMenuFrame(MenuVO menu) {
        return menu.getParentId().intValue() == 0 && GlobalConst.TYPE_MENU.equals(menu.getType())
                && GlobalConst.NO_FRAME.equals(menu.getFrame());
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public static String getRouterPath(MenuVO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && GlobalConst.TYPE_DIR.equals(menu.getType())
                && GlobalConst.NO_FRAME.equals(menu.getFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isInnerLink(MenuVO menu) {
        return GlobalConst.NO_FRAME.equals(menu.getFrame()) && StrUtil.ishttp(menu.getPath());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public static String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{GlobalConst.HTTP, GlobalConst.HTTPS},
                new String[]{"", ""});
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public static String getComponent(MenuVO menu) {
        String component = GlobalConst.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = GlobalConst.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = GlobalConst.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isParentView(MenuVO menu) {
        return menu.getParentId().intValue() != 0 && GlobalConst.TYPE_DIR.equals(menu.getType());
    }
}
