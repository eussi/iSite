package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.dao.RoleMapper;
import com.eussi.blog.modules.dao.UserRoleMapper;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.po.UserRole;
import com.eussi.blog.modules.service.RoleService;
import com.eussi.blog.modules.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wangxm on 2019/1/31
 */
@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public List<Long> listRoleIds(long userId) {
        List<UserRole> userRoles = userRoleMapper.findAllByUserId(userId);
        List<Long> roleIds = new ArrayList<Long>();
        for(UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        return roleIds;
    }

    @Override
    public List<Role> listRoles(long userId) {
        List<Long> roleIds = listRoleIds(userId);//获取该用户所有roleId
        List<Role> roles = new ArrayList<Role>();
        Map<Long, Role> longRoleMap = roleService.findByIds(new HashSet<>(roleIds));//找到所有roleId对应role
        return new ArrayList<>(longRoleMap.values());
    }

    @Override
    public Map<Long, List<Role>> findMapByUserIds(List<Long> userIds) {
        return null;
    }

    @Override
    public void updateRole(long userId, Set<Long> roleIds) {

    }
}
