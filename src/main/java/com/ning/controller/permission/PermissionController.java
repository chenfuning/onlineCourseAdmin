package com.ning.controller.permission;

import com.alibaba.fastjson.JSONArray;
import com.ning.pojo.Permission;
import com.ning.result.Results;
import com.ning.service.PermissionService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private Sid sid;
    /**
     * 所有的permission的数据，通过Jsonarray菜单树返回
     * @return
     */
    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission() {
        return permissionService.listAllPermission();
    }

    /**
     * 获取permission的所有值
     * @return
     */
    @GetMapping("/menuAll")
    @ResponseBody
    public Results getMenuAll(){
        return permissionService.getMenuAll();
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public String addPermission(Model model){
        model.addAttribute("Permission",new Permission());
        return "permission/permission-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public Results savePermission(@RequestBody Permission permission){
        permission.setPermissionid(sid.nextShort());
        return permissionService.save(permission);
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public String editPermission(Model model, Permission permission) {
        model.addAttribute("Permission",permissionService.getPermissionById(permission.getPermissionid()));
        return "permission/permission-add";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public Results updatePermission(@RequestBody  Permission permission) {
        return permissionService.updatePermission(permission);
    }
    /**
     * 删除菜单，包括菜单下的按钮
     * @param Permission
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:del')")
    public Results deletePermission(Permission Permission) {
        return permissionService.delete(Permission.getPermissionid());
    }

    /**
     * 动态菜单根据adminid查询permission
     * @param adminid
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public Results getMenu(String adminid) {
        return permissionService.getMenu(adminid);
    }
}
