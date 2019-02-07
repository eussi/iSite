package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.RolePermission;
import com.eussi.blog.modules.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl implements RolePermissionService {
    @Override
    public List<Permission> findPermissions(long roleId) {
        return null;
    }

    @Override
    public void deleteByRoleId(long roleId) {

    }

    @Override
    public void add(Set<RolePermission> rolePermissions) {

    }
}
