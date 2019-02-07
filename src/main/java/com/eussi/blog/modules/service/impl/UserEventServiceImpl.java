package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.service.UserEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class UserEventServiceImpl implements UserEventService {
    @Override
    public void identityPost(Long userId, long postId, boolean identity) {

    }

    @Override
    public void identityComment(Long userId, long commentId, boolean identity) {

    }
}
