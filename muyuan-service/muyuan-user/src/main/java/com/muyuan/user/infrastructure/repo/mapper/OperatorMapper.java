package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperatorMapper extends UserBaseMapper<OperatorDO> {

    String PHONE = "phone";

    String SHOP_ID = "shopId";

    String USERNAME = "username";

    int STATUS_OK = 0;
}
