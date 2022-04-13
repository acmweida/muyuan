package com.muyuan.system.domain.entity;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Data
public class DictTypeEntity extends DictType {

    private DictTypeRepo dictTypeRepo;


    /**
     * 初始化用户信息
     */
    public void initInstance() {
        setCreateTime(new Date());
        setCreateBy(SecurityUtils.getUserId());
    }


    /**
     * 保存用户信息
     * @return
     */
    @Transactional
    public boolean save() {
        if (dictTypeRepo.insert(this)) {
            return true;
        }
        return false;
    }

}
