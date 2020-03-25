package com.ning.controller.student;

import com.ning.pojo.Admin;
import com.ning.pojo.Comment;
import com.ning.pojo.Dto.AdminDto;
import com.ning.pojo.Role;
import com.ning.pojo.User;
import com.ning.result.PageTableRequest;
import com.ning.result.Results;
import com.ning.service.RoleService;
import com.ning.service.StudentService;
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
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 分页查询User
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/studentlist")
    @ResponseBody
    public Results<User> studentlist(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return studentService.getAllStudentsByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/deletestudent")
    @ResponseBody
    public Results<User> deletestudent(String id){
        studentService.deletestudent(id);
        return Results.success();
    }
}
