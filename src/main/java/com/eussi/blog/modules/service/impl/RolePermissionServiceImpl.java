package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.modules.dao.PermissionMapper;
import com.eussi.blog.modules.dao.RolePermissionMapper;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.RolePermission;
import com.eussi.blog.modules.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findPermissions(long roleId) {
        List<RolePermission> rps = rolePermissionMapper.findAllByRoleId(roleId);
        if (rps != null && rps.size() > 0) {
            List<Long> permissionIds = new ArrayList<Long>();
            for(RolePermission rolePermission : rps) {
                permissionIds.add(rolePermission.getId());
            }
            String inQuery = CommonUtils.concatInQuery("id", permissionIds, Consts.IN);

            return permissionMapper.findAllByIdIsIn(inQuery);
        }

        return null;
    }

    @Override
    public void deleteByRoleId(long roleId) {

    }

    @Override
    public void add(Set<RolePermission> rolePermissions) {

    }
}
