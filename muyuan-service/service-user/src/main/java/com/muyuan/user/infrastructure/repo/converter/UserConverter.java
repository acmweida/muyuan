package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.Merchant;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.infrastructure.repo.dataobject.MerchantDO;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserConverter {


    @Mappings({
            @Mapping(target = "id",source ="id.value"),
            @Mapping(target = "platformType",source ="platformType.code")
    })
    RoleDO to(Role role);

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(source = "username",target ="username.value")
    })
    Operator to(OperatorDO operatorDO);

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(source = "username",target ="username.value")
    })
    Merchant to(MerchantDO merchantDO);

    @Mappings({
            @Mapping(target = "id",source ="id.value"),
            @Mapping(target = "username",source ="username.value")
    })
    MerchantDO to(Merchant merchant);

    @Mappings({
            @Mapping(target = "id",source ="id.value"),
            @Mapping(target = "username",source ="username.value")
    })
    OperatorDO to(Operator operator);

    @Mappings({
            @Mapping(target = "id",expression = "java(UserConverter.map(roleDO.getId()))"),
            @Mapping(target = "platformType",expression = "java(PlatformType.trance(roleDO.getPlatformType()))")
    })
    Role toRole(RoleDO roleDO);


    List<Role> toRoles(List<RoleDO> roleDOS);

    List<Operator> toUsers(List<OperatorDO> operatorDOS);

    List<Merchant> toMerchants(List<MerchantDO> operatorDOS);

    static RoleID map(Long id) {
        return new RoleID(id);
    }



}
