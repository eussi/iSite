package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CommentMapper extends BaseMapper<Comment>{
    List<Comment> findByIdIn(Set<Long> ids);

    List<Comment> findAllByAuthorIdAndToIdOrderByCreatedDesc(long authorId, long toId);

    int deleteAllByIdIn(Collection<Long> ids);

    //自增并获得增加后主键
    void insertAndGetId(Comment po);
}