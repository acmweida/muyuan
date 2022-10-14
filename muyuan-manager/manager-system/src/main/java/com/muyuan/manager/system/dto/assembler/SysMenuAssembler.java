package com.muyuan.manager.system.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.manager.system.domains.model.SysMenu;
import com.muyuan.manager.system.dto.vo.MenuVO;
import com.muyuan.manager.system.dto.vo.SysMenuVO;
import com.muyuan.manager.system.dto.vo.SysMetaVo;
import com.muyuan.manager.system.dto.vo.SysRouterVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MenuAssembler
 * Description MenuAssembler 菜单权限
 * @Author 2456910384
 * @Date 2022/2/15 10:21
 * @Version 1.0
 */
public class SysMenuAssembler {

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
            SelectTree selectTree = new SelectTree(menu.getId(),menu.getName());
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

    public static List<SelectTree> buildMenuSelectTree(List<SysMenu> menus) {
        List<SelectTree> selectTrees = menus.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(item -> {
            return new SelectTree(item.getId(), item.getName());
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = selectTrees.stream().collect(Collectors.toMap(SelectTree::getId, item -> item));

        for (SysMenu menu : menus) {
            long parentId = menu.getParentId();
            long id = menu.getId();
            SelectTree selectTree = new SelectTree(menu.getId(),menu.getName());
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
     * @param menus
     * @return
     */
    public static List<SysMenuVO> buildMenuTree(List<SysMenu> menus) {
        List<SysMenuVO> roots = menus.stream().filter(item -> {
            return item.getParentId() == 0;
        }).map(item -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(item, sysMenuVO);
            return sysMenuVO;
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = roots.stream().collect(Collectors.toMap(SysMenuVO::getId, item -> item));

        for (SysMenu menu : menus) {
            long parentId = menu.getParentId();
            long id = menu.getId();
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(menu, sysMenuVO);
            if (parentId != 0) {

                if (treeMap.containsKey(parentId)) {
                    Object o = treeMap.get(menu.getParentId());
                    // 父节点 parentId 遍历过 但父节点没有遍历  保存该节点到当前字子节点列表
                    if (o instanceof Collection) {
                        ((Collection<SysMenuVO>) o).add(sysMenuVO);
                    } else
                        // 如果父节点已经遍历过 且加入其子节点
                        if (o instanceof SysMenuVO) {
                        ((SysMenuVO) o).getChildren().add(sysMenuVO);
                    }
                } else {
                    // 父节点 parentId 没有遍历过 先创建保存当前节点到parentId的子节点列表
                    List<SysMenuVO> childrens = new ArrayList<>();
                    childrens.add(sysMenuVO);
                    treeMap.put(parentId, childrens);
                }
                // 当前接口已存在 而节点没有遍历 这次节点为非叶子节点 直接设置当前节点的字节点
                if (treeMap.containsKey(id)) {
                    sysMenuVO.setChildren((ArrayList<SysMenuVO>) treeMap.get(id));
                }
                treeMap.put(id, sysMenuVO);
            }
        }

        return roots;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param sysMenu 菜单列表
     * @return 路由列表
     */
    public static SysMenuVO buildMenus(SysMenu sysMenu) {
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu,sysMenuVO);

        return sysMenuVO;
    }



    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public static List<SysRouterVo> buildMenus(List<SysMenuVO> menus) {
        List<SysRouterVo> routers = new LinkedList<SysRouterVo>();

        for (SysMenuVO menu : menus) {
            SysRouterVo router = new SysRouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new SysMetaVo(menu.getName(), menu.getIcon(), 1 == menu.getCache(), menu.getPath()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty()  && GlobalConst.TYPE_DIR.equals(menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<SysRouterVo> childrenList = new ArrayList<SysRouterVo>();
                SysRouterVo children = new SysRouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new SysMetaVo(menu.getName(), menu.getIcon(), 0 == menu.getCache(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new SysMetaVo(menu.getName(), menu.getIcon()));
                router.setPath("/inner");
                List<SysRouterVo> childrenList = new ArrayList<SysRouterVo>();
                SysRouterVo children = new SysRouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(GlobalConst.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new SysMetaVo(menu.getName(), menu.getIcon(), menu.getPath()));
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
    public static String getRouteName(SysMenuVO menu) {
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
    public static boolean isMenuFrame(SysMenuVO menu) {
        return menu.getParentId().intValue() == 0 && GlobalConst.TYPE_MENU.equals(menu.getType())
                && GlobalConst.NO_FRAME.equals(menu.getFrame());
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public static String getRouterPath(SysMenuVO menu) {
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
    public static boolean isInnerLink(SysMenuVO menu) {
        return  GlobalConst.NO_FRAME.equals(menu.getFrame())  && StrUtil.ishttp(menu.getPath());
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
    public static String getComponent(SysMenuVO menu) {
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
    public static boolean isParentView(SysMenuVO menu) {
        return menu.getParentId().intValue() != 0 && GlobalConst.TYPE_DIR.equals(menu.getType());
    }
}
