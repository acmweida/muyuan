package com.muyuan.manager.system.service.service;

import com.muyuan.manager.system.dto.vo.SysUserVO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户应用接口
 */
public interface SysUserApplicationService {


    /**
     * 获取用户信息
     * @return
     */
    Optional<SysUserVO> get(Long id);


}
