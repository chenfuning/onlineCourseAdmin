package com.ning.service;

import com.ning.pojo.User;
import com.ning.result.Results;

public interface StudentService {
    Results<User> getAllStudentsByPage(Integer offset, Integer limit);

    void deletestudent(String id);
}
