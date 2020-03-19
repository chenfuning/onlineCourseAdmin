package com.ning.service.impl;


import com.ning.mapper.RoleMapper;
import com.ning.mapper.RolepermissionMapper;
import com.ning.mapper.RoleuserMapper;
import com.ning.pojo.Dto.RoleDto;
import com.ning.pojo.Role;
import com.ning.pojo.Roleuser;
import com.ning.result.ResponseCode;
import com.ning.result.Results;
import com.ning.service.RoleService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoleServiceimpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolepermissionMapper rolepermissionMapper;
    @Autowired
    private  RoleuserMapper roleuserMapper;

    @Autowired
    private Sid sid;

    @Override
    public Results<Role> getAllRoles() {
        return Results.success(50,roleMapper.selectAll());
    }
    @Override
    public Role getRolenameByid(String roleIdtoStr) {
        return roleMapper.selectByPrimaryKey(roleIdtoStr);
    }

    @Override
    public Results<Role> getAllRolesByPage(Integer offset, Integer limit) {
        return Results.success(roleMapper.countAllRoles().intValue(),roleMapper.getAllRoleByPage(offset,limit));
    }

    @Override
    public Results save(RoleDto roleDto) {
        //保存角色
        roleDto.setRoleid(sid.nextShort());
        roleMapper.insert(roleDto);
        //保存角色对应的权限，这里的permissionid是前端获取到的存放在数组中
        List<String> permissionIds=roleDto.getPermissionIds();
        //移除0，permission id 是从1开始
        permissionIds.remove(0);
        if(!CollectionUtils.isEmpty(permissionIds)){
            //这里使用批量插入，mapper里面使用foreach标签
            rolepermissionMapper.save(roleDto.getRoleid(),permissionIds);
        }
        return Results.success();
    }

    @Override
    public Role getRoleById(String roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public Results update(RoleDto roleDto) {
        List<String> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0);
        //1、更新角色权限之前要删除该角色之前的所有权限
        rolepermissionMapper.deleteRolePermission(roleDto.getRoleid());

        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolepermissionMapper.save(roleDto.getRoleid(), permissionIds);
        }

        //3、更新角色表
        int countData = roleMapper.updateByPrimaryKey(roleDto);
        if(countData > 0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public Results<Role> delete(String roleid) {
        List<Roleuser> datas = roleuserMapper.listAllSysRoleUserByRoleId(roleid);
        if(datas.size() <= 0){
            roleMapper.deleteByPrimaryKey(roleid);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
    }

}
