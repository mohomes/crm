package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.exceptions.ParamsException;
import com.dev.model.UserModel;
import com.dev.query.UserQuery;
import com.dev.service.UserService;
import com.dev.utils.LoginUserUtil;
import com.dev.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/19 6:24
 * @ClassName UserController
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     *  用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userLogin(userName, userPwd);
        resultInfo.setResult(userModel);
       /* try {
            UserModel userModel = userService.userLogin(userName, userPwd);
            resultInfo.setResult(userModel);

        } catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        } catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("登录失败");

        }*/
        return resultInfo;
    }

    /**
     * 用户修改密码
     * @param request
     * @param oldPwd
     * @param newPwd
     * @param repeatPwd
     * @return
     */
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePassWord(HttpServletRequest request,
                                     String oldPwd,String newPwd,String repeatPwd){
        ResultInfo resultInfo = new ResultInfo();
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePassWord(userId,oldPwd,newPwd,repeatPwd);
       /* try {

        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        }catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("修改密码失败");
            e.printStackTrace();
        }*/
        return resultInfo;
    }

    /**
     * 查询所有的销售
     * @return
     */
    @RequestMapping("queryAllSales")
    @ResponseBody
    public List<Map<String,Object>> queryAllSales(){
        return userService.queryAllSales();
    }

    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    /**
     * 多条件查询列表
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "user/user";
    }

    /**
     * 跳转到用户添加页面
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer userId,HttpServletRequest request
                             ){
        if (userId!=null) {
            User user = userService.selectByPrimaryKey(userId);
            request.setAttribute("userInfo",user);
        }
        return "user/add_update";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user){
        userService.addUser(user);
        return success("用户添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户信息更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteByIds(ids);
        return success("用户删除成功");
    }
}
