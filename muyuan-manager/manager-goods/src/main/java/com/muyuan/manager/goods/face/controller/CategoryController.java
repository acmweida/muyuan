package com.muyuan.manager.goods.face.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.goods.domains.assembler.CategoryAssembler;
import com.muyuan.manager.goods.domains.dto.CategoryDTO;
import com.muyuan.manager.goods.domains.model.Category;
import com.muyuan.manager.goods.domains.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CategoryController
 * Description 商品类目控制器
 * @Author 2456910384
 * @Date 2022/6/9 15:27
 * @Version 1.0
 */
@RequestMapping("/category")
@Api(tags = {"商品类目接口"})
@RestController
@AllArgsConstructor
public class CategoryController {

    private CategoryService goodsCategoryService;

    @GetMapping("/list")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态", dataTypeClass = String.class, paramType = "query")}
    )
    public Result list(@ModelAttribute CategoryDTO categoryDTO) {
        List<Category> list = goodsCategoryService.list(categoryDTO);
        return ResultUtil.success(CategoryAssembler.buildListTree(list));
    }

    @PostMapping()
    @RequirePermissions("goods:category:add")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "code", value = "商品分类编码", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "orderNum", value = "显示顺序", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "logo", value = "分类图标", dataTypeClass = String.class, paramType = "body")}
    )
    public Result add(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryDTO.convert();
        if (GlobalConst.NOT_UNIQUE.equals(goodsCategoryService.checkUnique(category))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"商品分类已存在");
        }
        goodsCategoryService.add(categoryDTO);
        return ResultUtil.success();
    }

    @PutMapping()
    @ApiOperation("商品分类更新")
    @RequirePermissions("goods:category:edit")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "商品分类ID", dataTypeClass = Long.class, paramType = "body",required = true),
            @ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "orderNum", value = "显示顺序", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "logo", value = "分类图标", dataTypeClass = String.class, paramType = "body",required = true)}
    )
    public Result update(@RequestBody @Valid CategoryDTO categoryDTO) {
        if (ObjectUtils.isEmpty(categoryDTO.getId())) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(),"更新分类ID不能为空");
        }

        Category category = categoryDTO.convert();
        if (GlobalConst.NOT_UNIQUE.equals(goodsCategoryService.checkUnique(category))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"商品分类已存在");
        }

        goodsCategoryService.update(categoryDTO);
        return ResultUtil.success();
    }


    @GetMapping("/treeSelect")
    @RequirePermissions("goods:category:query")
    @ApiOperation("商品分类树型结构查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "parentId", value = "父节点ID", dataTypeClass = String.class, paramType = "body", defaultValue = "0"),
                    @ApiImplicitParam(name = "level", value = "层级", dataTypeClass = Integer.class, paramType = "body", defaultValue = "0")
            }
    )
    public Result treeSelect(@ModelAttribute CategoryDTO categoryDTO) {
        if (ObjectUtils.isEmpty(categoryDTO.getParentId()) && ObjectUtils.isEmpty(categoryDTO.getLevel())) {
            categoryDTO.setParentId(0L);
        }
        return ResultUtil.success(goodsCategoryService.treeSelect(categoryDTO.getParentId(), categoryDTO.getLevel()));
    }

    @ApiOperation("商品分类简单查询")
    @GetMapping("/{id}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path",required = true)}
    )
    public Result get(@PathVariable @Valid @NotBlank(message = "id不能未空") String id) {
        Optional<Category> goodsCategory = goodsCategoryService.get(Category.builder().id(Long.valueOf(id)).build());
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(), "商品分类信息未找到"));
    }

    @ApiOperation("商品分类详细查询")
    @GetMapping("/detail/{code}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "商品编码", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result detail(@PathVariable @Valid @NotBlank(message = "code不能未空") String code) {
        Optional<Category> goodsCategory = goodsCategoryService.detail(Category.builder()
                .code(Long.valueOf(code)).build());
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(), "商品分类信息未找到"));
    }

    /**
     * 通过商品编码 获取三级分类信息
     * @param code
     * @return
     */
    @ApiOperation("通过商品编码 获取三级分类信息")
    @GetMapping("/leaf/{code}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "商品编码", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result getLeaf(@PathVariable @Valid @NotBlank(message = "code不能未空") String code) {
        Optional<Category> goodsCategory = goodsCategoryService.get(Category.builder()
                .code(Long.valueOf(code))
                .leaf(GlobalConst.TRUE)
                .build());
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(), "商品分类信息未找到"));
    }


    /**
     * 通过商品编码 获取三级分类信息
     * @return
     */
    @ApiOperation("列表查询所有最终分类")
    @GetMapping("/leaf/selectOption")
    @RequirePermissions("goods:category:query")
    public Result selectOption() {
        List<Category> goodsCategorys = goodsCategoryService.list(CategoryDTO.builder()
                .leaf(GlobalConst.TRUE)
                .build());
        return ResultUtil.success(CategoryAssembler.buildSelect(goodsCategorys));
    }

    @ApiOperation("商品分类删除")
    @DeleteMapping("/{ids}")
    @RequirePermissions("goods:category:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result delete(@PathVariable Long[] ids) {
        goodsCategoryService.delete(ids);
        return ResultUtil.success();
    }
}
