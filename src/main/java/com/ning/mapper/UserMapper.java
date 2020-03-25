package com.ning.mapper;

import com.ning.pojo.Admin;
import com.ning.pojo.User;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    @Select("select count(*) from user t")
    Long countAllStudents();
    @Select("select * from user t order by t.id limit #{startPosition},#{limit}")
    List<User> getAllStudentsByPage(Integer startPosition, Integer limit);
}