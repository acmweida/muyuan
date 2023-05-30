package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.CategoryInterface;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryQueryRequest;
import com.muyuan.goods.api.dto.CategoryRequest;
import com.muyuan.goods.domains.service.CategoryService;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.face.dto.converter.CategoryDTOConverter;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @ClassName CategoryInterfaceApi
 * Description 内部接口  商品分类
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.GOODS, version = "1.0"
        , interfaceClass = CategoryInterface.class
)
public class CategoryInterfaceApi implements CategoryInterface {

    private CategoryDTOConverter MAPPER;

    private CategoryService categoryService;

    @Override
    public Result<Page<CategoryDTO>> list(CategoryQueryRequest request) {
        Page<Category> list = categoryService.list(MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(list, MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result<List<CategoryDTO>> listByBrandId(Long... categoryIds) {
        List<Category> list = categoryService.listByBrandId(categoryIds);
        return ResultUtil.success(MAPPER.toDTO(list));
    }

    @Override
    public Result add(CategoryRequest request) {
        if (categoryService.exists(new Category.Identify(request.getParentId(), request.getName()))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST);
        }
        boolean flag = categoryService.addCategory(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<CategoryDTO> getCategory(Long id) {
        Optional<Category> handler = categoryService.getCategory(id);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result<CategoryDTO> getCategoryByCode(Long code) {
        Optional<Category> handler = categoryService.getCategoryByCode(code);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateCategory(CategoryRequest request) {
        if (categoryService.exists(new Category.Identify(request.getId(), request.getParentId(), request.getName()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = categoryService.updateCategory(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteCategory(Long id) {
        if (categoryService.deleteCategoryById(id)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
