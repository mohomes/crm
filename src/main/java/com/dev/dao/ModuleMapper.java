package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.model.TreeDto;
import com.dev.vo.Module;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    List<TreeDto> queryAllModules();
}