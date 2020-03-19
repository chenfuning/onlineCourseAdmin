package com.ning.service;

import com.ning.pojo.Dto.RoleDto;
import com.ning.pojo.Role;
import com.ning.result.Results;

public interface RoleService {
    Results<Role> getAllRoles();

    Role getRolenameByid(String roleIdtoStr);

    Results<Role> getAllRolesByPage(Integer offset, Integer limit);

    Results save(RoleDto roleDto);

    Role getRoleById(String roleid);

    Results update(RoleDto roleDto);

    Results<Role> delete(String roleid);
}
