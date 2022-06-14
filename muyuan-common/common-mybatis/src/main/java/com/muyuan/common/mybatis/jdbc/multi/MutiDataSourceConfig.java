package com.muyuan.common.mybatis.jdbc.multi;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MutiDataSourceConfig
 * Description 多数据源配置
 * @Author 2456910384
 * @Date 2022/6/14 10:39
 * @Version 1.0
 */
@Data
@Slf4j
public class MutiDataSourceConfig {

    private List<String> readMethodPrefix;

    private List<String> writeMethodPrefix;

    private Map<Object,List<String>> readWriteSplit = new HashMap<>();

    public boolean isReadWriteSplitDateSource(String dataSourceId) {
        return readMethodPrefix != null && writeMethodPrefix != null &&
                (readMethodPrefix.contains(GlobalConst.SLAVE_PREFIX+dataSourceId)
                        || readWriteSplit.containsKey(GlobalConst.MASTER_PREFIX + dataSourceId));
    }

    public boolean isReadMethod(String methodName) {
        if (StrUtil.isEmpty(methodName)) {
            return false;
        }
        for (String prefix : readMethodPrefix) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWriteMethod(String methodName) {
        if (StrUtil.isEmpty(methodName)) {
            return false;
        }
        for (String prefix : writeMethodPrefix) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }


    public String getReadDateSourceId(String dataSourceId) {
        if (!readWriteSplit.containsKey(GlobalConst.SLAVE_PREFIX+dataSourceId)) {
            log.error("dateSource :{} not found!",dataSourceId);
            return null;
        }
        return random(readWriteSplit.get(GlobalConst.SLAVE_PREFIX + dataSourceId));
    }

    public String getWriteDateSourceId(String dataSourceId) {
        if (!readWriteSplit.containsKey(GlobalConst.MASTER_PREFIX+dataSourceId)) {
            log.error("dateSource :{} not found!",dataSourceId);
            return null;
        }
        return random(readWriteSplit.get(GlobalConst.MASTER_PREFIX + dataSourceId));
    }

    private String random(List<String> dataSourceIds) {
        if (dataSourceIds.size() == 1 ) {
            return dataSourceIds.get(0);
        }
        int index = (int) (System.currentTimeMillis() & dataSourceIds.size() - 1);
        return dataSourceIds.get(index);
    }

}
