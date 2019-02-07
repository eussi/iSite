package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    @Override
    public Page<Role> paging(Page page, String name) {
        return null;
    }

    @Override
    public List<Role> list() {
        return null;
    }

    @Override
    public Map<Long, Role> findByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public Role get(long id) {
        return null;
    }

    @Override
    public void update(Role r, Set<Permission> permissions) {

    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void activate(long id, boolean active) {

    }
}
