package com.ning.controller;


import com.ning.pojo.Dto.RoleDto;
import com.ning.pojo.Permission;
import com.ning.result.Results;
import com.ning.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("rolepermission")
@Slf4j
public class RolepermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public Results<Permission> listAllPermissionByRoleId(RoleDto roleDto){
        return permissionService.listByRoleId(roleDto.getRoleid());
    }
}
