package com.eussi.blog.modules.repository;


import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.RolePermission;

import java.util.List;

/**
 * @author - wangxm
 */
public interface RolePermissionRepository extends BaseRepository<RolePermission> {
    int deleteByRoleId(long roleId);
    int deleteByPermissionId(long permissionId);
    List<RolePermission> findAllByRoleId(long roleId);
}
