package com.muyuan.system.application.service;


import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

/**
 * @ClassName DictTypeService
 * Description 字典类型
 * @Author 2456910384
 * @Date 2022/3/31 11:42
 * @Version 1.0
 */
public interface DictTypeService {

    Page list(DictTypeDTO dictTypeDTO);

    /**
     * 新增
     * 0-注册成功 1-账户已存在
     * @param dictTypeDTO
     * @return
     */
    int add(DictTypeDTO dictTypeDTO);
}
