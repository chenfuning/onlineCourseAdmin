package com.ning.controller.role;

import com.ning.pojo.Dto.RoleDto;
import com.ning.pojo.Role;
import com.ning.result.PageTableRequest;
import com.ning.result.Results;
import com.ning.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public Results<Role> getAll(){
        return roleService.getAllRoles();
    }

    /**
     * 分页查询Role
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Results<Role> getRoles(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return roleService.getAllRolesByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public String addRole(Model model){
        model.addAttribute("Role",new Role());
        return "role/role-add";
    }
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Results saveRole(@RequestBody RoleDto roleDto){
        return roleService.save(roleDto);
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public String editRole(Model model, Role role) {
        model.addAttribute("Role",roleService.getRoleById(role.getRoleid()));
        return "role/role-edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Results updateRole(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:del')")
    public Results<Role> deleteRole(RoleDto roleDto){
        return roleService.delete(roleDto.getRoleid());
    }

}
