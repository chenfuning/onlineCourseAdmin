package com.ning.mapper;

import com.ning.pojo.Admin;
import com.ning.pojo.User;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
}