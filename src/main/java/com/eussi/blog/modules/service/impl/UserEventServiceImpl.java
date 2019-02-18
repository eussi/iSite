package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.dao.UserMapper;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = false)
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void identityPost(Long userId, long postId) {
        User upUser = new User();
        upUser.setId(userId);
        upUser.setSteps(Consts.IDENTITY_STEP);
        userMapper.updateUserPosts(upUser);
    }

    @Override
    public void identityComment(Long userId, long commentId, boolean identity) {

    }
}
