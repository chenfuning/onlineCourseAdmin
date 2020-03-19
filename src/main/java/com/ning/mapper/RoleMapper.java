package com.ning.mapper;

import com.ning.pojo.Role;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    @Select("select count(*) from role r")
    Long countAllRoles();
    @Select("select * from role r order by r.roleid limit #{startPosition},#{limit}")
    List<Role> getAllRoleByPage(Integer startPosition, Integer limit);
}