package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.modules.dao.CommentMapper;
import com.eussi.blog.modules.po.Comment;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.service.CommentService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.service.UserEventService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.CommentVO;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.modules.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created by wangxueming on 2019/2/15.
 */
@Service
@Transactional(readOnly = false)
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Override
    public Page<CommentVO> paging4Admin(Page page) {
        return null;
    }

    @Override
    public Page<CommentVO> paging4Home(Page page, long authorId) {
        Comment comment = new Comment();
        comment.setAuthorId(authorId);

        //查询总记录数
        //得到总记录数
        Long totalCount = commentMapper.getTotalCount(comment);
        page.setTotalCount(totalCount);

        comment.setOrderBy("  created desc");
        comment.setLimit(page.getStartIndex() + "," + page.getPageSize());
        List<Comment> comments = commentMapper.findAllByQuery(comment);

        List<CommentVO> rets = new ArrayList<>();
        Set<Long> parentIds = new HashSet<>();
        Set<Long> uids = new HashSet<>();
        Set<Long> postIds = new HashSet<>();

        for(Comment c : comments) {
            CommentVO cv = BeanMapUtils.copy(c);
            //找可能包含父节点的评论
            if (c.getPid() > 0) {
                parentIds.add(c.getPid());
            }
            uids.add(c.getAuthorId());
            postIds.add(c.getToId());
            rets.add(cv);
        }

        // 加载父节点
        buildParent(rets, parentIds);

        //填充评论作者
        buildUsers(rets, uids);

        //填充文章
        buildPosts(rets, postIds);

        page.setData(rets);
        return page;
    }

    @Override
    public Page<CommentVO> paging(Page page, long toId) {
        Comment comment = new Comment();
        comment.setToId(toId);

        //查询总记录数
        //得到总记录数
        Long totalCount = commentMapper.getTotalCount(comment);
        page.setTotalCount(totalCount);

        comment.setOrderBy("  created desc");
        List<Comment> comments = commentMapper.findAllByQuery(comment);

        List<CommentVO> rets = new ArrayList<>();
        Set<Long> parentIds = new HashSet<>();
        Set<Long> uids = new HashSet<>();

        for(Comment c : comments) {
            CommentVO cv = BeanMapUtils.copy(c);
            //找可能包含父节点的评论
            if (c.getPid() > 0) {
                parentIds.add(c.getPid());
            }
            uids.add(c.getAuthorId());
            rets.add(cv);
        }

        // 加载父节点
        buildParent(rets, parentIds);

        //填充评论作者
        buildUsers(rets, uids);

        page.setData(rets);
        return page;
    }

    @Override
    public Map<Long, CommentVO> findByIds(Set<Long> ids) {
        Comment comment = new Comment();
        comment.setIsIn(CommonUtils.concatInQuery("id", ids, Consts.IN));
        List<Comment> list = commentMapper.findAllByQuery(comment);

        Map<Long, CommentVO> ret = new HashMap<>();
        Set<Long> uids = new HashSet<>();

        for(Comment c : list) {
            uids.add(c.getAuthorId());
            ret.put(c.getId(), BeanMapUtils.copy(c));
        }

        buildUsers(ret.values(), uids);
        return ret;
    }

    @Override
    public long post(CommentVO comment) {
        Comment po = new Comment();
        po.setAuthorId(comment.getAuthorId());
        po.setToId(comment.getToId());
        po.setContent(comment.getContent());
        po.setCreated(new Date());
        po.setPid(comment.getPid());

        commentMapper.insertAndGetId(po);
        //自增本用户评论数
        userEventService.identityComment(po.getAuthorId(), po.getId(), true);
        return po.getId();
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public void delete(long id, long authorId) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if(comment!=null && comment.getAuthorId()==authorId) {
            // 判断文章是否属于当前登录用户
            Assert.isTrue(comment.getAuthorId()==authorId, "认证失败");
            commentMapper.deleteByPrimaryKey(id);
            long postId = comment.getToId();
            // 评论数评论数
            postService.identityComments(postId, false);
            //用户评论数减1
            userEventService.identityComment(authorId, id, false);
        }
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
        Set<Long> uids = new HashSet<>();
        for(Comment comment : queryResults) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            uids.add(comment.getAuthorId());
            results.add(commentVO);
        }

        //填充对象数据
        buildUsers(results, uids);

        return results;
    }

    @Override
    public long count() {
        return commentMapper.getTotalCount(new Comment());
    }

    //加载父节点
    private void buildParent(Collection<CommentVO> comments, Set<Long> parentIds) {
        if (!parentIds.isEmpty()) {
            Map<Long, CommentVO> pm = findByIds(parentIds);

            for(CommentVO cv : comments) {
                if (cv.getPid() > 0) {
                    cv.setParent(pm.get(cv.getPid()));
                }
            }

        }
    }

    private void buildUsers(Collection<CommentVO> comments, Set<Long> uids) {
        Map<Long, UserVO> userMap = userService.findMapByIds(uids);
        //填充评论的作者
        for(CommentVO cv : comments) {
            cv.setAuthor(userMap.get(cv.getAuthorId()));
        }
    }

    private void buildPosts(Collection<CommentVO> comments, Set<Long> postIds) {
        Map<Long, PostVO> postMap = postService.findMapByIds(postIds);
        for(CommentVO commentVO : comments) {
            commentVO.setPost(postMap.get(commentVO.getToId()));
        }
    }
}
