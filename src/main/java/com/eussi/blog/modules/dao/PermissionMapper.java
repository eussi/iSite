package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> findAllByIdIsIn(String inQuery);


    int countUsed(long permId);

    int maxWeight();
}