package com.ning.service.impl;

import com.ning.mapper.UserMapper;
import com.ning.pojo.User;
import com.ning.result.Results;
import com.ning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Results<User> getAllStudentsByPage(Integer offset, Integer limit) {
        return Results.success(userMapper.countAllStudents().intValue(),userMapper.getAllStudentsByPage(offset,limit));
    }

    @Override
    public void deletestudent(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
