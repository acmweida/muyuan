package com.muyuan.config.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigQueryCommand;
import com.muyuan.config.repo.ConfigRepo;
import com.muyuan.config.repo.converter.ConfigConverter;
import com.muyuan.config.repo.dataobject.ConfigDO;
import com.muyuan.config.repo.mapper.CommonMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConfigRepoImpl implements ConfigRepo {

    private CommonMapper configMapper;

    private ConfigConverter converter;

    @Override
    public Page<Config> select(ConfigQueryCommand command) {
        LambdaQueryWrapper<ConfigDO> wrapper = new LambdaQueryWrapper<ConfigDO>()
                .eq(ConfigDO::getId,command.getId())
                .eq(ConfigDO::getName,command.getName())
                .eq(ConfigDO::getConfigKey,command.getConfigKey())
                .eq(ConfigDO::getConfigValue,command.getConfigValue())
                .eq(ConfigDO::getType,command.getType());
;
        Page<Config> page = Page.<Config>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<ConfigDO> list = command.enablePage() ?
                configMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                configMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Config selectConfig(Long id) {
        ConfigDO configDO = configMapper.selectOne(new LambdaQueryWrapper<ConfigDO>()
                .eq(ConfigDO::getId, id));
        return converter.to(configDO);
    }

    @Override
    public Config selectConfig(Config.Identify identify) {
        ConfigDO configDO = configMapper.selectOne(new LambdaQueryWrapper<ConfigDO>().select(ConfigDO::getId)
                .eq(ConfigDO::getId, identify.getId()));

        return converter.to(configDO);
    }

    @Override
    public boolean addConfig(Config config) {
        ConfigDO to = converter.to(config);
        Integer count = configMapper.insert(to);
        return count > 0;
    }

    @Override
    public Config updateConfig(Config config) {
        LambdaQueryWrapper<ConfigDO> wrapper = new LambdaQueryWrapper<ConfigDO>()
                .eq(ConfigDO::getId, config.getId());

        ConfigDO configDO = configMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(configDO)) {
            configMapper.updateById(converter.to(config));
        }

        return converter.to(configDO);
    }

    @Override
    public List<Config> deleteBy(Long... ids) {
        LambdaQueryWrapper<ConfigDO> wrapper = new LambdaQueryWrapper<ConfigDO>()
                .in(ConfigDO::getId, ids);
        List<ConfigDO> configs = configMapper.selectList(wrapper);

        configMapper.delete(wrapper);

        return converter.to(configs);
    }

}
