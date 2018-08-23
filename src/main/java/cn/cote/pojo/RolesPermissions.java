package cn.cote.pojo;

public class RolesPermissions {
    private Integer rolesPermissionsId;

    private String rolesName;

    private String permissionsName;

    public Integer getRolesPermissionsId() {
        return rolesPermissionsId;
    }

    public void setRolesPermissionsId(Integer rolesPermissionsId) {
        this.rolesPermissionsId = rolesPermissionsId;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName == null ? null : rolesName.trim();
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName == null ? null : permissionsName.trim();
    }
}