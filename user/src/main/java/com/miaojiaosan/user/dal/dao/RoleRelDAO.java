package com.miaojiaosan.user.dal.dao;

import java.util.Date;

public class RoleRelDAO {
    private Long id;

    private Long userId;

    private Long roleId;

    private Long version;

    private Long modify;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getModify() {
        return modify;
    }

    public void setModify(Long modify) {
        this.modify = modify;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}