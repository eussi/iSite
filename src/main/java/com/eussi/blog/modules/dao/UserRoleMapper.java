package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole>{

    List<UserRole> findAllByUserId(long userId);

    List<UserRole> findAllByRoleId(long roleId);

    /**
     * 清除权限
     *
     * @param userId 用户ID
     */
    int deleteByUserId(long userId);

    List<UserRole> findAllByUserIdIn(UserRole query);
}