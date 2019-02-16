package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Role;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据角色名称查询角色
     *
     * @param inQuery 角色名称
     * @return 角色列表
     */
    List<Role> findAllByIdIsIn(String inQuery);

    List<Role> findAllByStatusOrderByIdDesc(int status);
}