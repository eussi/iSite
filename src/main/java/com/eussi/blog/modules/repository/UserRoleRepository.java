package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole> {
    List<UserRole> findAllByUserId(long userId);

    List<UserRole> findAllByUserIdIn(Collection<Long> userId);

    List<UserRole> findAllByRoleId(long roleId);

    /**
     * 清除权限
     *
     * @param userId 用户ID
     */
    int deleteByUserId(long userId);
}
