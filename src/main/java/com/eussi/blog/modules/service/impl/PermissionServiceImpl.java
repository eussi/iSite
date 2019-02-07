package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.service.PermissionService;
import com.eussi.blog.modules.vo.PermissionTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Page<Permission> paging(Page page, String name) {
        return null;
    }

    @Override
    public List<PermissionTree> tree() {
        return null;
    }

    @Override
    public List<PermissionTree> tree(int parentId) {
        return null;
    }

    @Override
    public List<Permission> list() {
        return null;
    }

    @Override
    public Permission get(long id) {
        return null;
    }
}
