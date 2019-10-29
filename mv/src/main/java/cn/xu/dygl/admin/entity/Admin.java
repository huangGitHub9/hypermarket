package cn.xu.dygl.admin.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private String adminId;
    private String adminName;
    private Integer adminAge;
    private String adminGender;
    private String adminPassword;


    public Admin() {
        super();
    }
    public Admin(String adminId, String adminName, Integer adminAge, String adminGender, String adminPassword) {

        this.adminId = adminId;
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminGender = adminGender;
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(Integer adminAge) {
        this.adminAge = adminAge;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminAge=" + adminAge +
                ", adminGender='" + adminGender + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }

}
