package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.po.Role;
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

    @Override
    public List<Long> listRoleIds(long userId) {
        return null;
    }

    @Override
    public List<Role> listRoles(long userId) {
        return null;
    }

    @Override
    public Map<Long, List<Role>> findMapByUserIds(List<Long> userIds) {
        return null;
    }

    @Override
    public void updateRole(long userId, Set<Long> roleIds) {

    }
}
