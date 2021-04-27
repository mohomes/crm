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
import java.util.Date;
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
            module.setParentId(-1);
        }
        // 父级菜单 parentId 二级|三级（）
        if(grade!=0){
            AssertUtil.isTrue(null==module.getParentId(),"父级菜单不能为空");
            AssertUtil.isTrue(null==moduleMapper.selectByPrimaryKey(module.getParentId()),"请指定父级菜单");
        }
        // 权限码 optValue 非空 并且不可重复
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空");
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByOptValue(module.getOptValue()),"权限码已存在");
        module.setIsValid((byte)1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.insertSelective(module)<1,"添加资源失败");
    }

    /**
     *  参数校验
     *      id 非空 数据存在
     *      层级 grade 非空 0|1|2
     *      模块名称非空 同一级下模块名称唯一
     *       非空 同一层级下模块名称唯一 （不包含当前修改记录本身）
     *      地址 url
     *          二级菜单grade =1 非空且同一层级不可重复（不包含当前记录本身）
     *      权限码 optValue
     *          非空 不可重复（不包含当前修改记录本身）
     *      修改时间 updateDate
     *  设置参数的默认值
     *  执行操作 判断受影响的行数
     * @param module
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module){
        AssertUtil.isTrue(null==module.getId(),"待更新记录不存在");
        Module temp = moduleMapper.selectByPrimaryKey(module.getId());
        AssertUtil.isTrue(null==temp,"待更新记录不存在");
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"模块名称不存在");
        temp=moduleMapper.queryModuleByGradeAndModuleName(grade,module.getModuleName());
        if (temp!=null) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"该层级下菜单名已存在");
        }
        if(grade==1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"URL不能为空");
            temp =moduleMapper.queryModuleByGradeAndUrl(grade,module.getUrl());
            if (temp!=null) {
                AssertUtil.isTrue(!(temp.getId()).equals(module.getId()),"该层级下菜单url已存在！");
            }
        }
        temp = moduleMapper.queryModuleByOptValue(module.getOptValue());
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空");
        if (temp!=null){
            AssertUtil.isTrue(!(temp.getId()).equals(module.getId()),"权限码已存在");
        }
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module)<1,"更新菜单失败");
    }

    /**
     * 删除操作
     *  如果是父菜单 底层全部删除
     *  如果是子菜单 则删除一个
     *  删除资源时，将对应的权限表的记录也删除 （判断权限表中是否存在关联数据 如果存在 则删除）
     * @param module
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModule(Integer id){
        AssertUtil.isTrue(null==id,"待删除记录不存在");
        Module temp = moduleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==temp,"待删除记录不存在");
        Integer count= moduleMapper.queryModuleByParentId(id);

        AssertUtil.isTrue(count>0,"该资源存在子记录，不可删除");
        count = permissionMapper.countPermissionByModuleId(id);
        if (count > 0){
            // 删除指定资源ID的权限记录
            permissionMapper.deletePermissionByModuleId(id);
        }
        temp.setIsValid((byte) 0);
        temp.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(temp)<1,"删除资源失败");
    }
}
