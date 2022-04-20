package com.muyuan.system.domain.entity;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Data
public class DictTypeEntity extends DictType {

    /**
     * 初始化用户信息
     */
    public void init() {
        setCreateTime(new Date());
        setCreateBy(SecurityUtils.getUserId());
    }

}
