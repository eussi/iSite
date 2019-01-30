package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author wangxm
 */
public interface CommentRepository extends BaseRepository<Comment> {
	List<Comment> findByIdIn(Set<Long> ids);
	List<Comment> findAllByAuthorIdAndToIdOrderByCreatedDesc(long authorId, long toId);
	int deleteAllByIdIn(Collection<Long> ids);
}
