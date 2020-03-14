package com.ning.pojo;

import javax.persistence.*;

public class Roleuser {
    @Id
    @Column(name = "userId")
    private String userid;

    @Id
    @Column(name = "roleId")
    private String roleid;

    /**
     * @return userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

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
}