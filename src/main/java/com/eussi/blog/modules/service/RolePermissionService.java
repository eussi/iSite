package com.eussi.blog.modules.service;

import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * @author - wangxm
 * @create - 2019/1/30
 */
public interface RolePermissionService {
    List<Permission> findPermissions(long roleId);
    void deleteByRoleId(long roleId);
    void add(Set<RolePermission> rolePermissions);

}
