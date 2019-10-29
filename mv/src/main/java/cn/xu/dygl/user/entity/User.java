package cn.xu.dygl.user.entity;

import cn.xu.dygl.history.entity.History;
import cn.xu.dygl.memPackage.entity.MemPackage;
import cn.xu.dygl.role.entity.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private String userId;
    private String userName;
    private String userPassword;
    private Date   userBirthday;
    private String userGender;
    private Integer userAge;
    private String userHeadImgPath;
    private String userHeadImgName;

    //用户和套餐是多对一
    private MemPackage memPackage;

    //用户会员的开始和到期的时间
    private Date userMemStartTime;
    private Date userMemEndTime;


    //用户和角色是多对多
    private List<Role> roleList = new ArrayList<Role>();

    //分页帮助字段
    private String roleId;



    //设置默认值
    public static String USER_HEADIMGNAME = "default2.jpg";
    public static String USER_HEADEIMGPATH="upload/user/";
    public User(String userId, String userName, String userPassword, Date userBirthday, String userGender, Integer userAge, String userHeadImgPath, String userHeadImgName) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userHeadImgPath = userHeadImgPath;
        this.userHeadImgName = userHeadImgName;
    }

    public User() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getUserMemStartTime() {
        return userMemStartTime;
    }

    public void setUserMemStartTime(Date userMemStartTime) {
        this.userMemStartTime = userMemStartTime;
    }

    public Date getUserMemEndTime() {
        return userMemEndTime;
    }

    public void setUserMemEndTime(Date userMemEndTime) {
        this.userMemEndTime = userMemEndTime;
    }

    public MemPackage getMemPackage() {
        return memPackage;
    }

    public void setMemPackage(MemPackage memPackage) {
        this.memPackage = memPackage;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserHeadImgPath() {
        return userHeadImgPath;
    }

    public void setUserHeadImgPath(String userHeadImgPath) {
        this.userHeadImgPath = userHeadImgPath;
    }

    public String getUserHeadImgName() {
        return userHeadImgName;
    }

    public void setUserHeadImgName(String userHeadImgName) {
        this.userHeadImgName = userHeadImgName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirthday=" + userBirthday +
                ", userGender='" + userGender + '\'' +
                ", userAge=" + userAge +
                ", userHeadImgPath='" + userHeadImgPath + '\'' +
                ", userHeadImgName='" + userHeadImgName + '\'' +
                '}';
    }
}
