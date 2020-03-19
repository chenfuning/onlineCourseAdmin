package com.ning.service;

import com.alibaba.fastjson.JSONArray;
import com.ning.pojo.Permission;
import com.ning.result.Results;

public interface PermissionService {
    /**
     * 查询所有权限的数据
     * @return
     */
    Results<JSONArray> listAllPermission();

    /**
     * 获取所有menuAll
     * @return
     */
    Results getMenuAll();

    /**
     * 保存permission
     * @param permission
     * @return
     */
    Results save(Permission permission);

    /**
     * 根据permissionid查询permission
     * @param
     * @return
     */
    Permission getPermissionById(String permissionid);

    /**
     * 更新permission
     * @param permission
     * @return
     */
    Results updatePermission(Permission permission);

    /**
     * 删除permission
     * @param permissionid
     * @return
     */
    Results delete(String permissionid);

    /**
     * 根据角色查询其permission
     * @param roleid
     * @return
     */
    Results<Permission> listByRoleId(String roleid);

    /**
     * 动态菜单根据adminid查询permission
     * @param adminid
     * @return
     */
    Results getMenu(String adminid);
}
