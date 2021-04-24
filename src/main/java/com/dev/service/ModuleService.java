package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.ModuleMapper;
import com.dev.dao.PermissionMapper;
import com.dev.model.TreeDto;
import com.dev.utils.AssertUtil;
import com.dev.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Map<String,Object> queryModuleList(){
        Map<String, Object> map = new HashMap<>();
        List<Module> list = moduleMapper.queryModuleList();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    /**
     * 参数校验
     *  模块名称不能为空
     *      非空 同一层级下模块名称唯一
     *  地址 url
     *      二级菜单 （grade=1） 非空且不可重复
     *  父级菜单
     *     一级菜单 （目录 grade=0） null
     *     二级菜单|三级菜单  （菜单|按钮 grade=1或者2） 非空 父级菜单必须存在
     *     层级 grade
     *      非空 0|1|2
     *     权限码 optValue 非空不重复
     * 设置参数默认值
     * 执行添加操作 判断受影响的行数
     * @param module
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module){
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"模块名称不能为空");
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByGradeAndModuleName(grade,module.getModuleName()),
                "该层级下模块名称已存在");
        if(grade==1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"URL不能为空");
            AssertUtil.isTrue(null!=moduleMapper.queryModuleByGradeAndUrl(grade,module.getUrl()),"URL不可重复");
        }

        if (grade==0){
            module.setParentId(null);
        }
        // 父级菜单 parentId 二级|三级（）
        if(grade!=0){
            AssertUtil.isTrue(null==module.getParentId(),"父级菜单不能为空");
            AssertUtil.isTrue(null==moduleMapper.selectByPrimaryKey((module.getParentId())),"请指定父级菜单");
        }
        // 权限码 optValue 非空
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空");
        // 权限码 optValue
        AssertUtil.isTrue(moduleMapper.insertSelective(module)<1,"菜单资源添加失败");
    }
}
