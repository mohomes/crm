package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.model.TreeDto;
import com.dev.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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


    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(){
        return moduleService.queryAllModules();
    }

}
