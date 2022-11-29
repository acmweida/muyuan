package com.muyuan.config.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigCommand;
import com.muyuan.config.face.dto.ConfigQueryCommand;
import com.muyuan.config.repo.ConfigRepo;
import com.muyuan.config.service.ConfigService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @ClassName ConfigDomainServiceImpl
 * Description 权限
 * @date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private ConfigRepo configRepo;

    @Override
    public Page<Config> list(ConfigQueryCommand commend) {
        return configRepo.select(commend);
    }

    @Override
    public String checkUnique(Config.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        Config config = configRepo.selectConfig(identify);
        if (null != config && !id.equals(config.getId())){
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean addConfig(ConfigCommand command) {
        Config config = new Config();

        config.setConfigName(command.getConfigName());
        config.setConfigKey(command.getConfigKey());
        config.setConfigValue(command.getConfigValue());
        config.setConfigType(command.getConfigType());
        config.setCreateBy(command.getCreateBy());
        config.setCreateTime(command.getCreateTime());
        config.setUpdateBy(command.getUpdateBy());
        config.setUpdateTime(command.getUpdateTime());
        config.setRemark(command.getRemark());

        return configRepo.addConfig(config);
    }

    @Override
    public Optional<Config> getConfig(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return configRepo.selectConfig(id_);
                });
    }

    @Override
    public boolean updateMenu(ConfigCommand command) {
        Config config = new Config();

        config.setId(command.getId());
        config.setConfigName(command.getConfigName());
        config.setConfigKey(command.getConfigKey());
        config.setConfigValue(command.getConfigValue());
        config.setConfigType(command.getConfigType());
        config.setCreateBy(command.getCreateBy());
        config.setCreateTime(command.getCreateTime());
        config.setUpdateBy(command.getUpdateBy());
        config.setUpdateTime(command.getUpdateTime());
        config.setRemark(command.getRemark());

        Config old = configRepo.updateConfig(config);
        if (ObjectUtils.isNotEmpty(old)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteConfigById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Integer> removeIds = new ArrayList(Arrays.asList(ids));

        List<Config> olds = configRepo.deleteBy(removeIds.toArray(new Integer[0]));

        return !olds.isEmpty();
    }


}
