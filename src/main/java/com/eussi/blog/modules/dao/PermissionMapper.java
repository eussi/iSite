package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission>{

    List<Permission> findAllByIdIsIn(String inQuery);

    int countUsed(long permId);

    int maxWeight();
}