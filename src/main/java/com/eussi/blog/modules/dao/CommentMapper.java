package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findByIdIn(Set<Long> ids);
    List<Comment> findAllByAuthorIdAndToIdOrderByCreatedDesc(long authorId, long toId);
    int deleteAllByIdIn(Collection<Long> ids);
}