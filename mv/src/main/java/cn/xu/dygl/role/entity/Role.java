package cn.xu.dygl.role.entity;

import cn.xu.dygl.privilege.entity.Privilege;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private String roleId;
    private String roleName;

    private List<Privilege> privilegeList;

    public Role() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", privilegeList=" + privilegeList +
                '}';
    }
}
