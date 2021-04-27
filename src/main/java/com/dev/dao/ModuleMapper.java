package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.model.TreeDto;
import com.dev.vo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    // 查询所有的资源列表
    List<TreeDto> queryAllModules();

    // 查询所有的资源数据
    List<Module> queryModuleList();

    // 根据层级和模块查询 模块名称是否重复
    Module queryModuleByGradeAndModuleName(@Param("grade") Integer grade,@Param("moduleName") String moduleName);

    // 通过层级和url查询资源对象
    Module queryModuleByGradeAndUrl(@Param("grade")Integer grade, @Param("url")String url);
    // 权限码查重
    Module queryModuleByOptValue(@Param("optValue")String optValue);

    Integer queryModuleByParentId(Integer id);
}