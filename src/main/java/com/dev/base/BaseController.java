package com.dev.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.base
 * @Description
 * @date 2021/4/18 20:35
 * @ClassName BaseController
 */
public class BaseController {

    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }


}
