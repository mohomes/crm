package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.RoleQuery;
import com.dev.service.RoleService;
import com.dev.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/22 13:28
 * @ClassName RoleController
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     *
     * 查询所有的角色列表
     * @return
     */
    @PostMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery roleQuery){
        return roleService.queryByParamsForTable(roleQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    /*跳转到添加或者更新角色页面*/
    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdate(Integer roleId, HttpServletRequest request){
        if (roleId!=null){
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role",role);
        }
        return "role/add_update";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("角色数据添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("角色数据更新成功");
    }

    /*逻辑删除角色记录*/
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer id){
        roleService.deleteRole(id);
        return success("角色记录删除成功");
    }

    @RequestMapping("toGrantRolePage")
    public String toGrantRolePage(Integer roleId,HttpServletRequest request){
        request.setAttribute("roleId",roleId);
        return "role/grant";
    }
}
