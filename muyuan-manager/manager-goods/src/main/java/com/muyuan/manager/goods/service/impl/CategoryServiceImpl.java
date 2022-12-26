package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.CategoryInterface;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryQueryRequest;
import com.muyuan.goods.api.dto.CategoryRequest;
import com.muyuan.manager.goods.dto.CategoryQueryParams;
import com.muyuan.manager.goods.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName GoodsCategoryDomainServiceImpl
 * Description 商品分类
 * @Author 2456910384
 * @Date 2022/6/10 8:57
 * @Version 1.0
 */
@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private CategoryInterface categoryInterface;

    @Override
    public Page<CategoryDTO> list(CategoryQueryParams params) {
        CategoryQueryRequest request = CategoryQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .leaf(params.getLeaf())
                .parentId(params.getParentId())
                .build();

        Result<Page<CategoryDTO>> res = categoryInterface.list(request);
        return res.getData();
    }


    @Override
    public Result add(CategoryRequest request) {
       return categoryInterface.add(request);
    }

    @Override
    public Result update(CategoryRequest request) {
        return  categoryInterface.updateCategory(request);
    }

    @Override
    public Optional<CategoryDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<CategoryDTO> permissioHander = categoryInterface.getCategory(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    @Override
    public  Optional<CategoryDTO> detail(Long code) {
        return Optional.of(code)
                .map(id_ -> {
                    Result<CategoryDTO> permissioHander = categoryInterface.getCategoryByCode(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    @Override
    public Result delete(Long id) {
        if (org.springframework.util.ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail();
        }
        return categoryInterface.deleteCategory(id);
    }

}
