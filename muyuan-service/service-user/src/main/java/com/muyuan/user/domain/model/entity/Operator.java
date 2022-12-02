package com.muyuan.user.domain.model.entity;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Operator {

    @Data
    static public class Identify {

        private UserID userID;

        private Username username;

        public Identify(Username username) {
            this.username = username;
        }

        public Identify(UserID userID, Username username) {
            this.userID = userID;
            this.username = username;
        }
    }

    private UserID id;

    /**
     * 用户名 唯一 用于登录
     */
    private Username username;

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


    private List<Role> roles;

    private Integer sex;

    private String email;

    public Operator(UserID id) {
        this.id = id;
    }

    public Operator(Username username) {
        this.username = username;
    }

    public Operator(Username username, String password) {
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


    /**
     * 初始化用户信息
     */
    public void initPassword() {
        Assert.isTrue(!ObjectUtils.isEmpty(getPassword()),"SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        setNickName(createUserName());
        setPassword(EncryptUtil.SHA1(getPassword() + salt, encryptKey));;
        setSalt(salt);
        setEncryptKey(encryptKey);
    }

}
