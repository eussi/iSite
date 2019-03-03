package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role>{
    /**
     * 根据角色名称查询角色
     *
     * @param inQuery 角色名称
     * @return 角色列表
     */
    List<Role> findAllByIdIsIn(String inQuery);

    List<Role> findAllByStatusOrderByIdDesc(int status);

    void insertAndGetId(Role r);
}