package com.muyuan.member.domains.model;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.domains.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName User
 * Description 用户 t_user
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    /**
     * 用户名 唯一 用于登录
     */
    private String username;

    private String nickName;

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


    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private static final String NAME_PREFIX = "my_";

    private static Random random = new Random();

    public static String createUserName() {
        StringBuffer name = new StringBuffer(NAME_PREFIX);
        name.append(StrUtil.randomString(7));
        name.append((random.nextInt(900)+100));
        return name.toString();
    }

    /**
     * 初始化用户信息
     */
    public void initInstance() {
        Assert.isTrue(!ObjectUtils.isEmpty(getPassword()),"SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        setNickName(createUserName());
        setPassword(EncryptUtil.SHA1(getPassword() + salt, encryptKey));;
        setSalt(salt);
        setEncryptKey(encryptKey);

        setCreateTime(new Date());
        if (SecurityUtils.isLogin()) {
            setCreateBy(SecurityUtils.getUserId());
            setCreator(SecurityUtils.getUsername());
        }
    }

    public void save(UserRepo userRepo) {
        Assert.notNull(userRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> userRepo.insert(this),
                        id -> {
                            update();
                            userRepo.update(this);
                        }
                );
    }

    public void update(UserRepo userRepo, String... column) {
        Assert.notNull(userRepo, "repo is null");
        Assert.notNull(id, "id is null");
        update();
        userRepo.update(this, ArrayUtils.addAll(column, "updateTime", "updateBy", "updater"));
    }

    public void addRole(UserRepo userRepo,Role role) {
        Assert.notNull(userRepo,"repo is null");
        Assert.notNull(role,"role is null");
        Assert.notNull(id,"id is null");
        userRepo.insert(UserRole.builder()
                .roleId(role.getId())
                .userId(id)
                .build());
    }
}
