package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.CommonUtils;
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
@Transactional(readOnly = false)
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
        UserRole query = new UserRole();
        query.setIsIn(CommonUtils.concatInQuery("user_id", userIds, Consts.IN));
        List<UserRole> list = userRoleMapper.findAllByUserIdIn(query);

        Map<Long, Set<Long>> userRoleMap = new HashMap<Long, Set<Long>>();
        for(UserRole userRole : list) {
            if(userRoleMap.get(userRole.getUserId()) == null) {
                Set<Long> roleIds = new HashSet<Long>();
                roleIds.add(userRole.getRoleId());
                userRoleMap.put(userRole.getUserId(), roleIds);
            } else {
                userRoleMap.get(userRole.getUserId()).add(userRole.getRoleId());
            }
        }

        Map<Long, List<Role>> rets =  new HashMap<Long, List<Role>>();
        for(Map.Entry entry : userRoleMap.entrySet()) {
            Set<Long> roleIds = (Set<Long>) entry.getValue();
            List<Role> roles = roleService.findListByIds(roleIds);
            rets.put((Long) entry.getKey(), roles);
        }
        return rets;
    }

    @Override
    public void updateRole(long userId, Set<Long> roleIds) {
        // 判断是否清空已授权角色
        if (null == roleIds || roleIds.isEmpty()) {
            userRoleMapper.deleteByUserId(userId);
        } else {
            List<UserRole> list = userRoleMapper.findAllByUserId(userId);
            List<Long> exitIds = new ArrayList<>();

            //多的删除，少的添上
            if (null != list) {
                for(UserRole userRole : list) {
                    if (!roleIds.contains(userRole.getRoleId())) {
                        userRoleMapper.deleteByPrimaryKey(userRole);
                    } else {
                        exitIds.add(userRole.getRoleId());
                    }
                }

            }

            for(Long roleId : roleIds) {
                if(!exitIds.contains(roleId)) {
                    UserRole po = new UserRole();
                    po.setUserId(userId);
                    po.setRoleId(roleId);

                    userRoleMapper.insert(po);
                }

            }
        }
    }
}
