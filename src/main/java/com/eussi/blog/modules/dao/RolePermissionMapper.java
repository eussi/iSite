package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.RolePermission;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int deleteByRoleId(long roleId);
    int deleteByPermissionId(long permissionId);
    List<RolePermission> findAllByRoleId(long roleId);
}