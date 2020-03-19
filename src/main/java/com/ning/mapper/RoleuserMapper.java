package com.ning.mapper;

import com.ning.pojo.Roleuser;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleuserMapper extends MyMapper<Roleuser> {
    @Select("select * from roleuser t where t.userId=#{userId}")
    Roleuser selectRoleUserByuserId(String userId);

    int updateroleuser(Roleuser roleuser);
    @Delete("delete from roleuser where userId=#{adminid}")
    int deleteRoleUserByUserId(String adminid);

    @Select("select * from roleuser t where t.roleId = #{roleid}")
    List<Roleuser> listAllSysRoleUserByRoleId(String roleid);
}