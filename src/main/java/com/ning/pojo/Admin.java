package com.ning.pojo;

import javax.persistence.*;

public class Admin {
    @Id
    private String adminid;

    private String name;

    private String password;

    private String admintype;

    private String email;

    private String phone;

    private Byte status;

    private String intro;

    private String bz;

    public interface Status {
        int DISABLED = 0;
        int VALID = 1;

    }
    /**
     * @return adminid
     */
    public String getAdminid() {
        return adminid;
    }

    /**
     * @param adminid
     */
    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return admintype
     */
    public String getAdmintype() {
        return admintype;
    }

    /**
     * @param admintype
     */
    public void setAdmintype(String admintype) {
        this.admintype = admintype;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * @return bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }
}