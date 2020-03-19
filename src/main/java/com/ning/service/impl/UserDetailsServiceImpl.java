package com.ning.service.impl;

import com.ning.mapper.PermissionMapper;
import com.ning.pojo.Admin;
import com.ning.pojo.Dto.LoginAdmin;
import com.ning.pojo.Permission;
import com.ning.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;//*!*adminService
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=userService.getAdmin(username);
        //判断该用户是否可用
        if (admin == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }
         else if (admin.getStatus() == Admin.Status.DISABLED) {
            throw new DisabledException("用户已作废");
        }
        LoginAdmin loginAdmin = new LoginAdmin();
        BeanUtils.copyProperties(admin, loginAdmin);
        //获取并赋给用户权限
        List<Permission> permissions = permissionMapper.listByAdminId(admin.getAdminid());
        loginAdmin.setPermissions(permissions);
        return loginAdmin;
    }
}
