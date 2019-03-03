package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.modules.dao.RoleMapper;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.po.RolePermission;
import com.eussi.blog.modules.service.RolePermissionService;
import com.eussi.blog.modules.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = false)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Override
    public Page<Role> paging(Page page, String name) {
        Role query = new Role();
        if(StringUtils.isNotBlank(name)) {
            query.setMatch("name like '%".concat(name).concat("%'"));
        }
        Long total = roleMapper.getTotalCount(query);
        page.setTotalCount(total);

        query.setOrderBy(" id desc");
        query.setLimit(page.getStartIndex() + "," + page.getPageSize());
        List<Role> list = roleMapper.findAllByQuery(query);

        page.setData(list);
        return page;
    }

    @Override
    public List<Role> list() {
        return roleMapper.findAll();
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
    public List<Role> findListByIds(Set<Long> roleIds) {
        String inQuery = CommonUtils.concatInQuery("id", roleIds, Consts.IN);
        return roleMapper.findAllByIdIsIn(inQuery);
    }

    @Override
    public Role get(long id) {
        Role role =  roleMapper.selectByPrimaryKey(id);
        return toVO(role);
    }

    @Override
    public void update(Role r, Set<Permission> permissions) {
        Role role = roleMapper.selectByPrimaryKey(r.getId());
        if(role==null) {
            roleMapper.insertAndGetId(r);
        } else {
            roleMapper.updateByPrimaryKeySelective(r);
        }

        //删除在添加权限
        rolePermissionService.deleteByRoleId(r.getId());
        if(permissions!=null && permissions.size()>0) {
            Set<RolePermission> rps = new HashSet<>();
            long roleId = r.getId();
            for(Permission p : permissions) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(p.getId());
                rps.add(rp);
            }
            rolePermissionService.add(rps);
        }
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
