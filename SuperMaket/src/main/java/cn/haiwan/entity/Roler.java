package cn.haiwan.entity;

import java.util.Date;

public class Roler {
    private Integer id;
    private String roleCode;
    private String roleName;
    private String createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;

    public Roler() {
        super();
    }

    public Roler(Integer id, String roleCode, String roleName,
                 String createdBy, Date creationDate, Integer modifyBy,
                 Date modifyDate) {
        super();
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Roler [id=" + id + ", roleCode=" + roleCode + ", roleName="
                + roleName + ", createdBy=" + createdBy + ", creationDate="
                + creationDate + ", modifyBy=" + modifyBy + ", modifyDate="
                + modifyDate + "]";
    }

}