package com.ning.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ning.mapper.PermissionMapper;
import com.ning.pojo.Permission;
import com.ning.result.Results;
import com.ning.service.PermissionService;
import com.ning.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class PermissionServiceimpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Results<JSONArray> listAllPermission() {
        List datas=permissionMapper.selectAll();
        JSONArray array=new JSONArray();
        //构建了一个JSONarray 菜单树
        TreeUtils.setPermissionsTree("0",datas, array);
        return Results.success(array);
    }

    @Override
    public Results<Permission> getMenuAll() {
        return Results.success(0,permissionMapper.selectAll());
    }

    @Override
    public Results save(Permission Permission) {
        return (permissionMapper.insert(Permission)>0)?Results.success():Results.failure();
    }

    @Override
    public Permission getPermissionById(String permissionid) {
        return permissionMapper.selectByPrimaryKey(permissionid);
    }

    @Override
    public Results updatePermission(Permission permission) {
        return (permissionMapper.updateByPrimaryKeySelective(permission)> 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(String permissionid) {
        permissionMapper.deleteByPrimaryKey(permissionid);
        Example permissionExample=new Example(Permission.class);
        Example.Criteria criteria=permissionExample.createCriteria();
        criteria.andEqualTo("parentid", permissionid);
        permissionMapper.deleteByExample(permissionExample);
        return Results.success();
    }
}
