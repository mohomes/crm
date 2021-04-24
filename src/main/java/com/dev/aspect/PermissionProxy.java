package com.dev.aspect;

import com.dev.annoation.RequiredPermission;
import com.dev.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.aspect
 * @Description
 * @date 2021/4/24 15:46
 * @ClassName PermissionProxy
 */
@Component
@Aspect
public class PermissionProxy {

    @Resource
    private HttpSession httpSession;

    /**
     * 切面会拦截指定包下的指定注解
     *      com.dev.annoation.RequiredPermission
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.dev.annoation.RequiredPermission)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result =null;
        // 得到当前登录用户拥有的权限
        List<String> permissions = (List<String>) httpSession.getAttribute("permissions");
        // 判断用户是否拥有权限
        if (null==permissions || permissions.size()<1){
            // 抛出认证异常
            throw new AuthException();
        }

        // 得到对应的目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 得到方法上对应的状态码
        RequiredPermission permission = signature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        // 判断注解上对应的状态码
        if (!(permissions.contains(permission.code()))){
            // 如果权限中不包含当前方法上注解指定的权限吗 则抛出异常
            throw new AuthException();
        }

        result = joinPoint.proceed();
        return result;
    }
}
