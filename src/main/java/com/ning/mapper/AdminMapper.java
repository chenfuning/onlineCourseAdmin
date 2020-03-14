package com.ning.mapper;

import com.ning.pojo.Admin;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper extends MyMapper<Admin> {
    /**
     * 查询用户总页数
     * @return
     */
    @Select("select count(*) from admin t")
    Long countAllUsers();
    /**
     * 返回查询到的用户list
     * @return
     */
    @Select("select * from admin t order by t.adminid limit #{startPosition},#{limit}")
    List<Admin> getAllUserByPage(@Param("startPosition")Integer startPosition, @Param("limit")Integer limit);
}