package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * 角色-权限值
 * @author wangxm
 *
 */
public class RolePermission extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -5979636077649378677L;

    private long id;

    private long roleId;

    private long permissionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }
}
