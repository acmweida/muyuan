package com.muyuan.common.web.aspect;

import com.muyuan.common.core.constant.Logical;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.exception.NotPermissionException;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于 Spring Aop 的注解鉴权
 * 
 * @author kong
 */
@Aspect
@Component
@AllArgsConstructor
public class PreAuthorizeAspect
{

    private RedisCacheManager redisCacheManager;

    /**
     * 构建
     */
    public PreAuthorizeAspect()
    {
    }

    /**
     * 定义AOP签名 (切入所有使用鉴权注解的方法)
     */
    public static final String POINTCUT_SIGN = "@annotation(com.muyuan.common.web.annotations.RequirePermissions)";

    /**
     * 声明AOP签名
     */
    @Pointcut(POINTCUT_SIGN)
    public void pointcut()
    {
    }

    /**
     * 环绕切入
     * 
     * @param joinPoint 切面对象
     * @return 底层方法执行后的返回值
     * @throws Throwable 底层方法抛出的异常
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        // 注解鉴权
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try
        {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        }
        catch (Throwable e)
        {
            throw e;
        }
    }

    /**
     * 对一个Method对象进行注解检查
     */
    public void checkMethodAnnotation(Method method)
    {

        // 校验 @RequiresPermissions 注解
        RequirePermissions requirePermissions = method.getAnnotation(RequirePermissions.class);
        if (requirePermissions != null)
        {
            checkPermi(requirePermissions);
        }
    }

    /**
     * 根据注解(@RequiresPermissions)鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param requiresPermissions 注解对象
     */
    public void checkPermi(RequirePermissions requiresPermissions)
    {
        if (requiresPermissions.logical() == Logical.AND)
        {
            checkPermiAnd(requiresPermissions.value());
        }
        else
        {
            checkPermiOr(requiresPermissions.value());
        }
    }

    /**
     * 验证用户是否含有指定权限，必须全部拥有
     *
     * @param permissions 权限列表
     */
    public void checkPermiAnd(String... permissions)
    {
        Set<String> permissionList = getUserPerm();
        for (String permission : permissions)
        {
            if (!hasPermi(permissionList, permission))
            {
                throw new NotPermissionException();
            }
        }
    }

    public Set<String> getUserPerm() {
        List<String> roles = SecurityUtils.getRoles();
        Set<String> permissionList = new HashSet<>();
        for (String role : roles) {
            Set<String> objects =  redisCacheManager.sGet(RedisConst.ROLE_PERM_KEY_PREFIX, role);
            if (null != objects){
                permissionList.addAll(objects);
            }
        }
        return permissionList;
    }

    /**
     * 验证用户是否含有指定权限，只需包含其中一个
     *
     * @param permissions 权限码数组
     */
    public void checkPermiOr(String... permissions)
    {
        Set<String> permissionList = getUserPerm();
        for (String permission : permissions)
        {
            if (hasPermi(permissionList, permission))
            {
                return;
            }
        }
        if (permissions.length > 0)
        {
            throw new NotPermissionException();
        }
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(Collection<String> authorities, String permission)
    {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> SecurityConst.ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(x, permission));
    }
}
