package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.ModuleMapper;
import com.dev.dao.PermissionMapper;
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

    @Resource
    private PermissionMapper permissionMapper;

    public List<TreeDto> queryAllModules(Integer roleId){
        // 查询所有的资源列表
        List<TreeDto> treeDtoList = moduleMapper.queryAllModules();
        // 查询指定角色已经授权过的资源列表 （查询角色拥有的角色ID）
        List<Integer> permissionList = permissionMapper.queryRoleHasModuleByRoleId(roleId);
        // 判断是否拥有资源ID
        if (permissionList!=null && permissionList.size()>0){
            treeDtoList.forEach(treeDto -> {
                // 判断角色拥有的资源Id中是否有当前资源遍历的资源ID
                if (permissionList.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtoList;
    }
}
