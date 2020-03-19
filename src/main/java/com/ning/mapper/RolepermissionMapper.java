package com.ning.mapper;

import com.ning.pojo.Rolepermission;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface RolepermissionMapper extends MyMapper<Rolepermission> {
    void save(String roleid, List<String> permissionIds);

    /**
     * 在rolepermission表中删除角色的权限
     * @param roleid
     */
    @Delete("delete from rolepermission where roleId = #{roleid}")
    void deleteRolePermission(String roleid);
}