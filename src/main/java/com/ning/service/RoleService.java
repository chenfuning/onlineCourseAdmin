package com.ning.service;

import com.ning.pojo.Role;
import com.ning.result.Results;

public interface RoleService {
    Results<Role> getAllRoles();

    Role getRolenameByid(String roleIdtoStr);
}
