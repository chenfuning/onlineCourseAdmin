package com.ning.pojo;

import javax.persistence.*;

public class Rolepermission {
    @Id
    @Column(name = "roleId")
    private String roleid;

    @Id
    @Column(name = "permissionId")
    private String permissionid;

    /**
     * @return roleId
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return permissionId
     */
    public String getPermissionid() {
        return permissionid;
    }

    /**
     * @param permissionid
     */
    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }
}