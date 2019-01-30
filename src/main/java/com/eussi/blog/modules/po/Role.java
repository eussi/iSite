package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * @author wangxm
 *
 */public class Role extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -1153854616385727165L;

    public static int STATUS_NORMAL = 0;
    public static int STATUS_CLOSED = 1;

    public static String ROLE_ADMIN = "admin";

    public static long ADMIN_ID = 1;

    private long id;

    private String name;

    private String description;

    private int status;

    private List<Permission> permissions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
