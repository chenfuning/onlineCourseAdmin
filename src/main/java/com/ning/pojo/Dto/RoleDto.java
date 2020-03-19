package com.ning.pojo.Dto;

import com.ning.pojo.Role;

import java.util.List;

public class RoleDto extends Role {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<String> permissionIds;

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
