package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.MerchantDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MerchantMapper extends UserBaseMapper<MerchantDO> {

    String PHONE = "phone";

    String SHOP_ID = "shopId";

    String USERNAME = "username";

    int STATUS_OK = 0;

    Integer addRef(@Param("userId") Long userId, @Param("roleIds") Long... roleIds);

    List<MerchantDO> selectAllocatedList(@Param("roleId") Long roleId,@Param("params") Map params);

    List<MerchantDO> selectUnallocatedList(@Param("roleId") Long roleId,@Param("params") Map params);
}
