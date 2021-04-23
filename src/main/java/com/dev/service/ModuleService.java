package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.ModuleMapper;
import com.dev.model.TreeDto;
import com.dev.vo.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/23 0:12
 * @ClassName ModuleService
 */
@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    public List<TreeDto> queryAllModules(){
        return moduleMapper.queryAllModules();
    }
}
