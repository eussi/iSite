package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Role;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends BaseRepository<Role> {
    /**
     * 根据角色名称查询角色
     *
     * @param ids 角色名称
     * @return 角色列表
     */
    List<Role> findAllByIdIsIn(Set<Long> ids);

    List<Role> findAllByStatusOrderByIdDesc(int status);
}
