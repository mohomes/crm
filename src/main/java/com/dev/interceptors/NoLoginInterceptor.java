package com.dev.interceptors;

import com.dev.dao.UserMapper;
import com.dev.exceptions.NoLoginException;
import com.dev.service.UserService;
import com.dev.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.interceptors
 * @Description 非法访问拦截
 * @date 2021/4/19 21:22
 * @ClassName NologinInterceptor
 */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    /**
     *  拦截用户是否是登录状态
     *   在目标方法（目标资源）执行前 ，执行的方法
     *
     *   方法返回boolean 类型
     *     true 可以执行
     *     false 不可以
     *
     *    如果判断用户是否是登录状态
     *      1、判断cookie中是否存在用户信息
     *      2、数据库中是否存在指定用户ID的值
     *
     *    如果用户是登录状态
     *     则允许目标方法执行；
     *     如果用户是非登录状态，则抛出未登录异常
     *      （在全局异常中做判断，如果未登录异常，则跳转到登录页面）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 判断用户ID是否为空  且数据库中存在该ID的用户记录
        if (null == userId || userService.selectByPrimaryKey(userId)==null) {
            // 抛出未登录异常
            throw new NoLoginException();
        }
        return true;
    }
}
