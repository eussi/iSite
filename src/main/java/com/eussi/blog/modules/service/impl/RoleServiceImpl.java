package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.modules.dao.RoleMapper;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.service.RolePermissionService;
import com.eussi.blog.modules.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Override
    public Page<Role> paging(Page page, String name) {
        return null;
    }

    @Override
    public List<Role> list() {
        return null;
    }

    @Override
    public Map<Long, Role> findByIds(Set<Long> roleIds) {
        Map<Long, Role> ret = new LinkedHashMap<>();
        String inQuery = CommonUtils.concatInQuery("id", roleIds, Consts.IN);
        List<Role> roles = roleMapper.findAllByIdIsIn(inQuery);
        for(Role role : roles) {
            Role roleVO = toVO(role);
            ret.put(roleVO.getId(), roleVO);
        }
        return ret;
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

    private Role toVO(Role po) {
        Role r = new Role();
        BeanUtils.copyProperties(po, r);
        r.setPermissions(rolePermissionService.findPermissions(r.getId()));
        return r;
    }
}
