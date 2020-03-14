package com.ning.service;

import com.ning.pojo.Dto.AdminDto;
import com.ning.result.Results;
import com.ning.pojo.Admin;

public interface UserService {
    /**
     * 分页查询admin的list
     * @param offset
     * @param limit
     * @return
     */
    Results<Admin> getAllUsersByPage(Integer offset, Integer limit);

    /**
     * 添加admin的数据
     * @param adminDto
     * @param roleId
     * @return
     */
    Results<Admin> save(AdminDto adminDto, String roleId);

    /**
     * 根据adminid查询admin
     * @param adminid
     * @return
     */
    Admin getUserById(String adminid);

    /**
     * 更新admin
     * @param adminDto
     * @param roleId
     * @return
     */
    Results<Admin> updateAdmin(AdminDto adminDto, String roleId);

    /**
     * 删除admin通过adminid
     * @param adminid
     * @return
     */
    int deleteAdmin(String adminid);
}
