package com.muyuan.member.interfaces.assembler;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.vo.MenuVO;
import com.muyuan.member.domain.vo.MetaVo;
import com.muyuan.member.domain.vo.RouterVo;
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
public class MenuAssembler {

    /**
     * 构建目录树结构
     * @param menus
     * @return
     */
    public static List<MenuVO> buildMenuTree(List<Menu> menus) {
        List<MenuVO> roots = menus.stream().filter(item -> item.getParentId() == 0).map(item -> {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(item, menuVO);
            return menuVO;
        }).collect(Collectors.toList());

        Map<Long, Object> treeMap = roots.stream().collect(Collectors.toMap(MenuVO::getId, item -> item));

        for (Menu menu : menus) {
            long parentId = menu.getParentId();
            long id = menu.getId();
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
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
                    treeMap.put(parentId, Arrays.asList(menuVO));
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
    public static List<RouterVo> buildMenus(List<Menu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();


        for (Menu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(), 1 == menu.getCache(), menu.getPath()));
            List<Menu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/inner");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
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
    public String getRouteName(Menu menu) {
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
    public boolean isMenuFrame(Menu menu) {
        return menu.getParentId().intValue() == 0 && GlobalConst.TYPE_MENU.equals(menu.getType())
                && menu.getFrame() == GlobalConst.NO_FRAME;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && GlobalConst.TYPE_DIR.equals(menu.getType())
                && GlobalConst.NO_FRAME == menu.getFrame()) {
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
    public boolean isInnerLink(Menu menu) {
        return menu.getFrame() == GlobalConst.NO_FRAME && StrUtil.ishttp(menu.getPath());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{GlobalConst.HTTP, GlobalConst.HTTPS},
                new String[]{"", ""});
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu) {
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
    public boolean isParentView(Menu menu) {
        return menu.getParentId().intValue() != 0 && GlobalConst.TYPE_DIR.equals(menu.getType());
    }
}
