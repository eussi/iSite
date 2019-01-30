package com.eussi.blog.modules.service;

/**
 * @author wangxm on 2019/1/30.
 */
public interface UserEventService {
    /**
     * 自增发布文章数
     * @param userId
     * @param postId
     */
    void identityPost(Long userId, long postId, boolean identity);

    /**
     * 自增评论数
     * @param userId
     * @param commentId
     */
    void identityComment(Long userId, long commentId, boolean identity);

}
