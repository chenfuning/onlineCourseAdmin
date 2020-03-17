package com.ning.service.impl;

import com.ning.mapper.RoleuserMapper;
import com.ning.pojo.Dto.AdminDto;
import com.ning.pojo.Roleuser;
import com.ning.result.Results;
import com.ning.mapper.AdminMapper;
import com.ning.pojo.Admin;
import com.ning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleuserMapper roleuserMapper;

    @Override
    public Results<Admin> getAllUsersByPage(Integer offset, Integer limit) {
        return Results.success(adminMapper.countAllUsers().intValue(),adminMapper.getAllUserByPage(offset,limit));
    }

    @Override
    public Results<Admin> save(AdminDto adminDto, String roleId) {
        if(roleId!=null){
            //存储user表
            adminMapper.insert(adminDto);
            //存储RoleUser表
            Roleuser roleuser=new Roleuser();
            roleuser.setRoleid(roleId);
            roleuser.setUserid(adminDto.getAdminid());
            roleuserMapper.insert(roleuser);
            return  Results.success();
        }
        return Results.failure();
    }

    @Override
    public Admin getUserById(String adminid) {
        return adminMapper.selectByPrimaryKey(adminid);
    }

    @Override
    public Results<Admin> updateAdmin(AdminDto adminDto, String roleId) {
        if(roleId!=null) {
            //修改user表
            adminMapper.updateByPrimaryKeySelective(adminDto);
            //修改roleuser表或者保存（看是否设置了角色）
            Roleuser roleuser = new Roleuser();
            roleuser.setUserid(adminDto.getAdminid());
            roleuser.setRoleid(roleId);
            if (roleuserMapper.selectRoleUserByuserId(adminDto.getAdminid()) != null) {
                //角色表有记录就采用更新的方法
                roleuserMapper.updateroleuser(roleuser);
            } else {
                roleuserMapper.insert(roleuser);
            }
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public int deleteAdmin(String adminid) {
        //删除roleuser表
        roleuserMapper.deleteRoleUserByUserId(adminid);
        //删除用户表
        int delResult =adminMapper.deleteByPrimaryKey(adminid);
        return  delResult;
    }

    @Override
    public Results<Admin> getAdminByFuzzyName(String username, Integer offset, Integer limit) {
        //提供中的条数，和具体的数据
        return Results.success(adminMapper.getAdminByFuzzyname(username).intValue(),adminMapper.getAdminByFuzzynameByPage(username,offset,limit));
    }
}
