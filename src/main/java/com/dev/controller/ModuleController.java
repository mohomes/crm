package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.model.TreeDto;
import com.dev.service.ModuleService;
import com.dev.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/23 0:18
 * @ClassName ModuleController
 */
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;


    @GetMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(Integer roleId){
        return moduleService.queryAllModules(roleId);
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryModuleList(){
        return moduleService.queryModuleList();
    }

    @RequestMapping("index")
    public String index(){
        return "module/module";
    }

    @RequestMapping("toAddModulePage")
    public String toAddModulePage(Integer grade, Integer parentId, HttpServletRequest request){
        request.setAttribute("grade",grade);
        request.setAttribute("parentId",parentId);
        return "module/add_update";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addModule(Module module){
        moduleService.addModule(module);
        return success("添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module){
        moduleService.updateModule(module);
        return success("更新菜单成功");
    }

    @RequestMapping("toUpdateModulePage")
    public String toUpdateModulePage(Integer id,HttpServletRequest request){
        Module module = moduleService.selectByPrimaryKey(id);
        request.setAttribute("module",module);
        return "module/update";
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteModule(Integer id){
        moduleService.deleteModule(id);
        return success("删除资源成功");
    }

}
