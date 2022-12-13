package com.muyuan.user.domain.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.domain.service.OperatorService;
import com.muyuan.user.face.dto.OperatorCommand;
import com.muyuan.user.face.dto.UserQueryCommand;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName OperatorDomainServiceImpl
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:07
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private OperatorRepo repo;

    @Override
    public Page<Operator> list(UserQueryCommand commend) {
        return repo.select(commend);
    }

    @Override
    public Optional<Operator> getUserByUsername(String username) {

        Operator operator = repo.selectOneByUsername(new Username(username), PlatformType.OPERATOR);

        return Optional.ofNullable(operator);
    }

    @Override
    public Optional<Operator> getOperatorByyId(UserID userId,PlatformType platformType) {
        return Optional.of(userId)
                .map(id_ -> {
                    return repo.selectOneByID(id_,platformType);
                });
    }

    @Override
    public String checkUnique(Operator.Identify identify) {
        Long id = null == identify.getUserID() ? 0 : identify.getUserID().getValue();
        Operator operator = repo.selectOperator(identify);
        if (null != operator && !id.equals(operator.getId().getValue())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOperator(OperatorCommand command) {

        Operator.OperatorBuilder builder  = Operator.builder();

        builder.username( new Username(command.getUsername()) );
        builder.nickName( command.getNickName() );
        builder.password( command.getPassword() );
        builder.avatar( "" );
        builder.phone( command.getPhone() );
        if ( command.getStatus() != null ) {
            builder.status(command.getStatus());
        }
        builder.createTime(DateTime.now().toDate());
        builder.createBy( command.getCreateBy() );
        builder.creator(command.getCreator());

        Operator operator = builder.build();

        operator.initPassword();

        repo.insert(operator);

        repo.insertRef(operator.getId(),command.getRoleIds());

        return true;
    }

    @Override
    public Page<Operator> selectAllocatedList(UserQueryCommand command) {
        return repo.selectAllocatedList(command);
    }

    @Override
    public Page<Operator> selectUnallocatedList(UserQueryCommand command) {
        return repo.selectUnallocatedList(command);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean authRole(UserID userID, List<RoleID> roleIDs) {
        repo.deleteRef(userID);
       return repo.insertRef(userID,roleIDs.toArray(new RoleID[0]));
    }
}
