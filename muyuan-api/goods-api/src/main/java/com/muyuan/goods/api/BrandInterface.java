package com.muyuan.goods.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.api.dto.BrandRequest;

import java.util.List;

/**
 * @ClassName BrandInterface 接口
 * Description 品牌
 * @Author 2456910384
 * @Date 2022/12/7 15:50
 * @Version 1.0
 */
public interface BrandInterface {

    /**
     * 菜单列表查询
     * @param request
     * @return
     */
    Result<Page<BrandDTO>> list(BrandQueryRequest request);

    /**
     * 根据品牌ID查询关联类目
     * @param categoryCodes
     * @return
     */
    Result<List<BrandDTO>> listByCCategoryCode(Long... categoryCodes);

    /**
     * 连接类目
     * @param brandId
     * @param categoryCodes
     * @return
     */
    Result linkCategory(Long brandId,Long... categoryCodes);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    Result<BrandDTO> get(Long id);

    /**
     * 添加
     * @param request
     * @return
     */
    Result add(BrandRequest request);


    /**
     * 添加
     * @param request
     * @return
     */
    Result update(BrandRequest request);

    /**
     * 修改认证状态
     * @param id
     * @param auditStatus
     * @return
     */
    Result audit(Long id,Integer auditStatus);

    /**
     *  删除品牌数据
     * @param ids
     * @return
     */
    Result delete(Long... ids);

}
