package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.User;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByIdIn(Set<Long> ids);

    void updateUserPosts(User upUser);
}