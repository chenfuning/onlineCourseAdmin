package com.ning.pojo;

import javax.persistence.*;

public class Permission {
    @Id
    @Column(name = "permissionId")
    private String permissionid;

    @Column(name = "parentId")
    private String parentid;

    private String name;

    private String css;

    private String href;

    private Boolean type;

    private String permission;

    private Integer sort;

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

    /**
     * @return parentId
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
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
     * @return css
     */
    public String getCss() {
        return css;
    }

    /**
     * @param css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return type
     */
    public Boolean getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * @return permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}