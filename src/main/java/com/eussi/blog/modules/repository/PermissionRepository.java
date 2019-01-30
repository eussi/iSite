package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author wangxm on 2019/1/30.
 */
public interface PermissionRepository extends BaseRepository<Permission> {
    List<Permission> findAllByIdIsIn(Set<Long> id);


    int countUsed(long permId);

    int maxWeight();
}
