package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.service.PermissionService;
import com.dev.service.UserService;
import com.dev.utils.LoginUserUtil;
import com.dev.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/18 20:33
 * @ClassName IndexController
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 进入首页之后 查询角色拥有的权限资源码
     * @param request
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        // 查询用户对象 设置作用域
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        // 判断当前角色拥有的资源列表 （查询对应资源的授权码）
        List<String> permissionCodes = permissionService.queryUserHasRoleHasPermission(userId);
        request.getSession().setAttribute("permissions",permissionCodes);
        return "main";
    }
}
