package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.PermissionMapper;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.service.PermissionService;
import com.eussi.blog.modules.vo.PermissionTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Page<Permission> paging(Page page, String name) {
        return null;
    }

    @Override
    public List<PermissionTree> tree() {
        Permission query = new Permission();
        query.setOrderBy(" weight desc, id asc");
        List<Permission> data = permissionMapper.findAllByQuery(query);

        //构建map存储
        Map<Long, PermissionTree> map = new LinkedHashMap<>();
        for (Permission po : data) {
            PermissionTree m = new PermissionTree();
            BeanUtils.copyProperties(po, m);
            map.put(po.getId(), m);
        }

        //构建权限树
        List<PermissionTree> results = new LinkedList<>();
        for (PermissionTree m : map.values()) {
            if (m.getParentId() == 0) {//如果没有父节点，是根节点
                results.add(m);
            } else {
                PermissionTree p = map.get(m.getParentId());//否则，找到父节点
                if (p != null) {//如果父节点不为空，添加到父节点上，注意不能出现无根节点的节点
                    p.addItem(m);//注意添加的都是引用，map引用和result引用都是一个值
                }
            }
        }
        return results;
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
