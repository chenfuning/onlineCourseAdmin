package com.ning.pojo;

import java.util.Date;
import javax.persistence.*;

public class Role {
    @Id
    @Column(name = "roleId")
    private String roleid;

    private String rolename;

    private String description;

    @Column(name = "crateTime")
    private Date cratetime;

    @Column(name = "updateTime")
    private Date updatetime;

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
     * @return rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return crateTime
     */
    public Date getCratetime() {
        return cratetime;
    }

    /**
     * @param cratetime
     */
    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}