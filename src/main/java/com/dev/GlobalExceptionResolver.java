package com.dev;

import com.alibaba.fastjson.JSON;
import com.dev.base.ResultInfo;
import com.dev.exceptions.AuthException;
import com.dev.exceptions.NoLoginException;
import com.dev.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev
 * @Description 全局异常统一处理类
 * @date 2021/4/19 20:23
 * @ClassName GlobalExceptionoResolver
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {



    /**
     * 异常处理方法
     *   方法的返回值：
     *    1、返回视图
     *    2、返回数据（json数据）
     *
     *   如何判断方法的返回值
     *        通过方法上是否声明@ResponseBody注解
     *              如果未声明 则表示返回视图
     *              如果声明了 则表示返回数据
     * @param httpServletRequest  request请求对象
     * @param httpServletResponse   response响应对象
     * @param o 方法对象
     * @param e 异常对象
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse, Object o, Exception e) {

        /**
         *  非法请求拦截
         *      判断是否抛出未登录异常
         *       如果抛出该异常 ，则要求用户登录，重定向跳转到登录页面
         */

        if (e instanceof NoLoginException){
            // 重定向登录页
            ModelAndView view = new ModelAndView("redirect:/index");
            return view;
        }
        /**
         * 设置默认异常处理（返回视图 ）
         */
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code",500);
        modelAndView.addObject("msg","系统异常，请重试...");

        if (o instanceof HandlerMethod){
            // 类型转换
            HandlerMethod handlerMethod = (HandlerMethod) o;
            // 获取方法上声明的 @ResponseBody对象
            ResponseBody responseBody=handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            // 判断ResponseBody对象是否为空 （如果对象为空，则表示返回的是视图；如果不为空，则表示返回的是数据）
            if (responseBody==null) {
                /**
                 * 返回视图
                 */
                // 判断异常类型
                if (e instanceof ParamsException) {
                    ParamsException exception = (ParamsException) e;
                    modelAndView.addObject("code",exception.getCode());
                    modelAndView.addObject("msg",exception.getMsg());

                }else if(e instanceof AuthException){
                    AuthException exception = (AuthException) e;
                    modelAndView.addObject("code",exception.getCode());
                    modelAndView.addObject("msg",exception.getMsg());
                }
                return modelAndView;

            }else{
                /**
                 * 返回数据
                 */
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("系统异常，请重试！");

                if (e instanceof ParamsException){
                    ParamsException exception = (ParamsException) e;
                    resultInfo.setCode(exception.getCode());
                    resultInfo.setMsg(exception.getMsg());
                }else if(e instanceof AuthException){
                    AuthException exception = (AuthException) e;
                    resultInfo.setCode(exception.getCode());
                    resultInfo.setMsg(exception.getMsg());
                }
                // 设置响应类型及编码格式 （响应JSON格式数据）
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                // 得到字符输出流
                PrintWriter out = null;
                try {
                    // 得到输出流
                    out = httpServletResponse.getWriter();
                    // 将需要返回的对象转换成JSON格式的数据
                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }finally {
                    // 如果对象不为空，则关闭
                    if (out!=null){
                        out.close();
                    }
                }
                return null;
            }
        }

        return modelAndView;
    }
}
