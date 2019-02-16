package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.CommentMapper;
import com.eussi.blog.modules.po.Comment;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.service.CommentService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.CommentVO;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/15.
 */
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<CommentVO> paging4Admin(Page page) {
        return null;
    }

    @Override
    public Page<CommentVO> paging4Home(Page page, long authorId) {
        return null;
    }

    @Override
    public Page<CommentVO> paging(Page page, long toId) {
        return null;
    }

    @Override
    public Map<Long, CommentVO> findByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public long post(CommentVO comment) {
        return 0;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public void delete(long id, long authorId) {

    }

    @Override
    public List<Comment> findAllByAuthorIdAndToId(long authorId, long toId) {
        return null;
    }

    @Override
    public List<CommentVO> latests(int maxResults) {
        Comment commentQuery = new Comment();
        commentQuery.setOrderBy(" created desc");
        commentQuery.setLimit("0," + maxResults);
        List<Comment> queryResults = commentMapper.findAllByQuery(commentQuery);
        // 填充对象数据
        List<CommentVO> results = new ArrayList<CommentVO>();
        for(Comment comment : queryResults) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);

            results.add(commentVO);
        }

        return results;
    }

    @Override
    public long count() {
        return 0;
    }
}
