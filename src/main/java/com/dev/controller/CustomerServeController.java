package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.CustomerServeQuery;
import com.dev.service.CustomerServeService;
import com.dev.utils.LoginUserUtil;
import com.dev.vo.CustomerServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yebai
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/5/1 13:05
 * @ClassName CustomerServeController
 */
@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;

    /**
     * 查询服务分配的列表
     * @param customerServeQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectCustomerServes(CustomerServeQuery customerServeQuery, Integer flag,
                                                   HttpServletRequest request){
        if (flag!=null && flag==1){
            customerServeQuery.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request));
        }
        return customerServeService.queryByParamsForTable(customerServeQuery);
    }

    /**
     * 通过不同的类型进入不同的页面
     * @param index
     * @return
     */
    @RequestMapping("index/{index}")
    public String index(@PathVariable("index")Integer index){
        if(index!=null){
            if (index==1){
                // 服务创建
                return "customerServe/customer_serve";
            }else if(index==2){
                // 服务分配
                return "customerServe/customer_serve_assign";
            }else if(index==3){
                // 服务处理
                return "customerServe/customer_serve_proceed";
            }else if(index==4){
                //  服务反馈
                return "customerServe/customer_serve_back";
            }else if(index==5){
                // 服务归档
                return "customerServe/customer_serve_archive";
            }
        }
        return "";
    }

    @RequestMapping("openCustomerServeDialog")
    public String openCustomerServeDialog(){
        return "customerServe/customer_serve_add";
    }

    // 进入服务分配子页面
    @RequestMapping("openCustomerServeAssignPage")
    public String openCustomerServeAssignDialog(Integer id, Model model){
        model.addAttribute("customerServe",customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_assign_child";
    }
    /**
     * 服务创建
     * @param customerServe
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe){
        customerServeService.addCustomerServe(customerServe);
        return success("服务创建成功");
    }

    /**
     * 服务更新操作
     * @param customerServe
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerServeState(CustomerServe customerServe){
        customerServeService.updateCustomerServeState(customerServe);
        return success("服务更新成功");
    }

    @RequestMapping("openCustomerServeProceedPage")
    public String openCustomerServeProceedPage(Integer id,HttpServletRequest request){
        request.setAttribute("customerServe",customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_proceed_add";
    }


    /**
     * 进入服务反馈添加页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("openCustomerServeBackPage")
    public String openCustomerServeBackPage(Integer id,HttpServletRequest request){
        request.setAttribute("customerServe",customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_back_add";
    }
}
