package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.RolePermission;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission>{

    int deleteByRoleId(long roleId);

    int deleteByPermissionId(long permissionId);

    List<RolePermission> findAllByRoleId(long roleId);
}