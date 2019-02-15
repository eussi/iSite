package com.eussi.blog.modules.service;


import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.po.Comment;
import com.eussi.blog.modules.vo.CommentVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangxm
 *
 */
public interface CommentService {
	Page<CommentVO> paging4Admin(Page page);

	Page<CommentVO> paging4Home(Page page, long authorId);

	/**
	 * 查询评论列表
	 * @param page
	 * @param toId
	 */
	Page<CommentVO> paging(Page page, long toId);

	Map<Long, CommentVO> findByIds(Set<Long> ids);
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	long post(CommentVO comment);
	
	void delete(List<Long> ids);

	/**
	 * 带作者验证的删除
	 * @param id
	 * @param authorId
	 */
	void delete(long id, long authorId);

	List<Comment> findAllByAuthorIdAndToId(long authorId, long toId);

	List<CommentVO> latests(int maxResults);

	long count();
}
