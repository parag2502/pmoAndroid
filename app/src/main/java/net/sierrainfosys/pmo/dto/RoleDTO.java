package net.sierrainfosys.pmo.dto;

import java.util.ArrayList;

public class RoleDTO {
    private String id;
    private String role;
    private String status;
    private PermissionDTO permissionDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PermissionDTO getPermissionDTO() {
        return permissionDTO;
    }

    public void setPermissionDTO(PermissionDTO permissionDTO) {
        this.permissionDTO = permissionDTO;
    }
}
