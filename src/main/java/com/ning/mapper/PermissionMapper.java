package com.ning.mapper;

import com.ning.pojo.Permission;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {

    @Select("select p.* from permission p inner join rolepermission rp on p.permissionId = rp.permissionId where rp.roleId = #{roleid} order by p.sort")
    List<Permission> listByRoleId(String roleid);
    /**
     *  先通过userrole表根据userid找到roleid，再通过rolepermission表找到permission的具体数据
     * @return
     */
    @Select("SELECT DISTINCT sp.*  " +
            "FROM roleuser sru " +
            "INNER JOIN rolepermission srp ON srp.roleId = sru.roleId " +
            "LEFT JOIN permission sp ON srp.permissionId = sp.permissionId " +
            "WHERE " +
            "sru.userId = #{adminid}")
    List<Permission> listByAdminId(String adminid);
}