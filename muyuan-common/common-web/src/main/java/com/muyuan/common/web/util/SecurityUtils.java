package com.muyuan.common.web.util;

import com.muyuan.common.web.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * 权限获取工具类
 * 
 * @author ruoyi
 */
public class SecurityUtils
{
    /**
     * 获取用户ID
     */
    public static Long getUserId()
    {
        return SecurityContextHolder.getUserId();
    }

    /**
     * 获取用户ID
     */
    public static Long getShopId()
    {
        Long shopId = SecurityContextHolder.getShopId();
        return  shopId == 0 ? null : shopId;
    }

    public static boolean isLogin() {
        return SecurityContextHolder.isLogin();
    }

    /**
     * 获取用户名称
     */
    public static String getUsername()
    {
        return SecurityContextHolder.getUserName();
    }

    public static List<String> getRoles() {
        return SecurityContextHolder.getRoles();
    }

    public static String getUserType() {
        return SecurityContextHolder.getUserType();
    }

    /**
     * 获取用户key
     */
    public static String getUserKey()
    {
        return SecurityUtils.getUserKey();
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
