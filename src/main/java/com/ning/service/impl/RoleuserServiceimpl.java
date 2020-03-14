package com.ning.service.impl;

import com.ning.mapper.RoleuserMapper;
import com.ning.pojo.Roleuser;
import com.ning.result.Results;
import com.ning.service.RoleuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RoleUserSerivceImpl implements RoleuserService {
    @Autowired
    private RoleuserMapper roleuserMapper;
    @Override
    public Results getSysRoleUserByUserId(String userId) {

        Roleuser roleUser=roleuserMapper.selectRoleUserByuserId(userId);
        if(roleUser!=null){
            return  Results.success(roleUser);
        }else{
            return Results.success();
        }
    }
}
