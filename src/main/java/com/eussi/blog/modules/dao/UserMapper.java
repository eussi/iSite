package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.User;

import java.util.List;
import java.util.Set;

public interface UserMapper extends BaseMapper<User>{

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByIdIn(Set<Long> ids);

    //增加或减少文章数
    void updateUserPosts(User upUser);

    //增加或减少评论数
    void updateUserComments(User upUser);

    User getByUsername(String username);

    void insertAndGetId(User po);
}