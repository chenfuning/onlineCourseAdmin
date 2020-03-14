package com.ning.controller.role;

import com.ning.mapper.RoleuserMapper;
import com.ning.result.Results;
import com.ning.service.RoleuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roleuser")
public class RoleUserController {
    @Autowired
    private RoleuserService roleuserService;

    /**
     * 根据userid在roleuser表中查询role的值
     * @return
     */
    @PostMapping("/getRoleUserByUserId")
    public Results getRoleUserByUserId(String userId){
        return roleuserService.getSysRoleUserByUserId(userId);
    }

}