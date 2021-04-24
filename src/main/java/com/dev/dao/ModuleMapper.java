package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.model.TreeDto;
import com.dev.vo.Module;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    // 查询所有的资源列表
    List<TreeDto> queryAllModules();

    // 查询所有的资源数据
    List<Module> queryModuleList();

    // 根据层级和模块查询 模块名称是否重复
    Module queryModuleByGradeAndModuleName(Integer grade, String moduleName);


    Module queryModuleByGradeAndUrl(Integer grade, String url);
}