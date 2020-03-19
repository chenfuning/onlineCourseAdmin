package com.ning.controller.user;

import com.ning.mapper.RoleMapper;
import com.ning.pojo.Dto.AdminDto;
import com.ning.pojo.Role;
import com.ning.result.PageTableRequest;
import com.ning.result.Results;
import com.ning.pojo.Admin;
import com.ning.service.RoleService;
import com.ning.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Sid sid;
    @Autowired
    private RoleService roleService;
    /**
     * 分页查询User
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Results<Admin> getUsers(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }
    /**
     * 跳转到user-add页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public String addUser(Model model){
        model.addAttribute(new Admin());
        return "user/user-add";
    }
    /**
     * user-add页面
     * @parama dminDto
     * @param roleId
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Results<Admin> saveUser(AdminDto adminDto, Integer roleId){
        //用户是否可用，默认让他可用
        Byte a=1;
        adminDto.setStatus(a);
        String adminid=sid.nextShort();
        adminDto.setAdminid(adminid);
        String roleIdtoStr= Integer.toString(roleId);
        //密码用springScurity自带加密
        adminDto.setPassword(new BCryptPasswordEncoder().encode(adminDto.getPassword()));
//        //密码用MD5加密
//        //userDto.setPassword(MD5.crypt(userDto.getPassword()));

        //根据角色id查询角色名，设置到admin的角色名上
        Role r=roleService.getRolenameByid(roleIdtoStr);
        adminDto.setAdmintype(r.getRolename());
        return userService.save(adminDto,roleIdtoStr);
    }
    /**
     * 修改用户跳转
     * @param model
     * @param admin
     * @return
     */
    @GetMapping("/edit")
    public String editAdmin(Model model,Admin admin){
        System.out.println(1111);
        model.addAttribute(userService.getUserById(admin.getAdminid()));
        return "user/user-edit";
    }
    /**
     * 修改form表单提交
     * @param
     * @param roleId
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Results<Admin> updateUser(AdminDto adminDto,String roleId){
        //根据roleId更改adminDto.type的值
        adminDto.setAdmintype(roleService.getRolenameByid(roleId).getRolename());
        return userService.updateAdmin(adminDto,roleId);
    }
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:del')")
    public Results deleteUser(AdminDto adminDto){
        int count=userService.deleteAdmin(adminDto.getAdminid());
        if(count>0)
            return Results.success();
        else{
            return Results.failure();
        }
    }
    /**
     * 模糊查询用户
     * @param pageTableRequest
     * @param
     * @return
     */
    @GetMapping("/findAdminByFuzzyName")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Results<Admin> findAdminByFuzzyName(PageTableRequest pageTableRequest, String name){
        pageTableRequest.countOffset();
        return userService.getAdminByFuzzyName(name,pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }
}
