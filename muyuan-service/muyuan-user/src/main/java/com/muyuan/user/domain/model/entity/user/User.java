package com.muyuan.user.domain.model.entity.user;

import com.muyuan.common.core.enums.UserType;
import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName User
 * Description 用户
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UserID id;

    /**
     * 用户名 唯一 用于登录
     */
    private Username username;

    private String nickName;

    private UserType type;
    /**
     * 店铺号
     */
    private Long shopId;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 加密salt
     */
    private String salt;

    /**
     * 加密key
     */
    private String encryptKey;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账号状态 0-正常 1-删除 2-锁定
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上次登录时间
     */
    private Date lastSignTime;

    private Long updateBy;

    private String updater;

    private String creator;

    private Long createBy;


    private List<Role> roles;


    private void update(long userId, String username) {
        updateTime = DateTime.now().toDate();
        updateBy = userId;
        updater = username;
    }

    public User(UserID id) {
        this.id = id;
    }

    public User(Username username) {
        this.username = username;
    }

    public User(Username username, String password) {
        this.username = username;
        this.password = password;
    }

    private static final String NAME_PREFIX = "my_";

    private static Random random = new Random();

    public static String createUserName() {
        StringBuffer name = new StringBuffer(NAME_PREFIX);
        name.append(StrUtil.randomString(7));
        name.append((random.nextInt(900) + 100));
        return name.toString();
    }

    public boolean isOperator() {
        return UserType.OPERATOR.equals(type);
    }

    public boolean isMerchant() {
        return UserType.MERCHANT.equals(type);
    }

    public boolean isMember() {
        return UserType.MEMBER.equals(type);
    }

    /**
     * 初始化用户信息
     */
    public void initInstance(long userId, String username) {
        Assert.isTrue(!ObjectUtils.isEmpty(getPassword()), "SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        setNickName(createUserName());
        setPassword(EncryptUtil.SHA1(getPassword() + salt, encryptKey));

        setSalt(salt);
        setEncryptKey(encryptKey);

        setCreateTime(new Date());
        setCreateBy(userId);
        setCreator(username);
    }

//    public void save(OperatorRepo operatorRepo, long userId, String username) {
//        Assert.notNull(operatorRepo, "repo is null");
//        FunctionUtil.of(id)
//                .ifThen(
//                        () -> operatorRepo.insert(this),
//                        id -> {
//                            update(userId, username);
//                            operatorRepo.update(this);
//                        }
//                );
//    }

//    public void update(OperatorRepo operatorRepo, String username, long userid, String... column) {
//        Assert.notNull(operatorRepo, "repo is null");
//        Assert.notNull(id, "id is null");
//        update(userid, username);
//        operatorRepo.update(this, ArrayUtils.addAll(column, "updateTime", "updateBy", "updater"));
//    }

//    public void addRole(OperatorRepo operatorRepo, Role role) {
//        Assert.notNull(operatorRepo, "repo is null");
//        Assert.notNull(role, "role is null");
//        Assert.notNull(id, "id is null");
//        operatorRepo.insert(UserRole.builder()
//                .roleId(role.getId())
//                .userId(id.getValue())
//                .build());
//    }
}
