package cn.xu.dygl.privilege.entity;

import java.io.Serializable;

public class Privilege implements Serializable {

    private String privilegeId;
    private String privilegeName;
    private String privilegeNameAs;

    public Privilege() {
        super();
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeNameAs() {
        return privilegeNameAs;
    }

    public void setPrivilegeNameAs(String privilegeNameAs) {
        this.privilegeNameAs = privilegeNameAs;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "privilegeId='" + privilegeId + '\'' +
                ", privilegeName='" + privilegeName + '\'' +
                ", privilegeNameAs='" + privilegeNameAs + '\'' +
                '}';
    }
}
