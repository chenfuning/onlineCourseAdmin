package com.ning.service.impl;


import com.ning.mapper.RoleMapper;
import com.ning.pojo.Role;
import com.ning.result.Results;
import com.ning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceimpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Results<Role> getAllRoles() {
        return Results.success(50,roleMapper.selectAll());
    }
    @Override
    public Role getRolenameByid(String roleIdtoStr) {
        return roleMapper.selectByPrimaryKey(roleIdtoStr);
    }

}
