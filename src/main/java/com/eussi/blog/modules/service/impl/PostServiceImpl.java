package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.PostAttributeMapper;
import com.eussi.blog.modules.dao.PostMapper;
import com.eussi.blog.modules.dao.UserMapper;
import com.eussi.blog.modules.po.Channel;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.po.PostAttribute;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.modules.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = false)
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostAttributeMapper postAttributeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ServletContext servletContext;

    @Override
    public Page<PostVO> paging(Page page, int channelId, Set<Integer> excludeChannelIds, String ord) {
        Post postQuery = new Post();

        if (channelId > Consts.ZERO) {
            postQuery.setChannelId(channelId);
        }

        if (null != excludeChannelIds && !excludeChannelIds.isEmpty()) {
            StringBuilder notInSb = new StringBuilder(" channelId not in (");
            for(Integer chanelId : excludeChannelIds) {
                notInSb.append(chanelId + ", ");
            }
            postQuery.setNotIn(notInSb.toString().
                    substring(0, notInSb.toString().length() - 1).
                    concat(") "));
        }

        //得到总记录数
        Long totalCount = postMapper.getTotalCount(postQuery);
        page.setTotalCount(totalCount);

        //得到总页数
        page.setTotalPage(totalCount/page.getPageSize() + 1);

        //得到记录
        StringBuilder orderBySb = new StringBuilder();
        if (Consts.order.FAVOR.equals(ord)) {
            orderBySb.append(" favors desc");
        } else if (Consts.order.HOTTEST.equals(ord)) {
            orderBySb.append(" comments desc");
        } else {
            orderBySb.append(" weight desc");
        }
        orderBySb.append(", created desc");

        if (Consts.order.HOTTEST.equals(ord)) {
            orderBySb.append(", views desc");
        }

        postQuery.setOrderBy(orderBySb.toString());

        postQuery.setLimit(page.getStartIndex() + "," + page.getPageSize());

        List<Post> queryResults = postMapper.findAllByQuery(postQuery);

        // 填充对象数据
        List<PostVO> results = new ArrayList<PostVO>();
        for(Post post : queryResults) {
            PostVO postVO = BeanMapUtils.copy(post);

            //文章内容
            fillPostPO(post, postVO);

            results.add(postVO);
        }

        page.setData(results);
        return page;
    }

    @Override
    public Page<PostVO> paging4Admin(Page page, long id, String title, int channelId) {
        return null;
    }

    @Override
    public Page<PostVO> pagingByAuthorId(Page page, long userId) {
        return null;
    }

    @Override
    public List<PostVO> findAllFeatured() {
        return null;
    }

    @Override
    public List<PostVO> findLatests(int maxResults, long ignoreUserId) {
        return null;
    }

    @Override
    public List<PostVO> findHots(int maxResults, long ignoreUserId) {
        return null;
    }

    @Override
    public Map<Long, PostVO> findMapByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public long post(PostVO post) {
        return 0;
    }

    @Override
    public PostVO get(long id) {
        Post post = postMapper.selectByPrimaryKey(id);
        PostVO postVO = BeanMapUtils.copy(post);

        fillPostPO(post, postVO);

        return postVO;
    }

    @Override
    public void update(PostVO p) {

    }

    @Override
    public void updateFeatured(long id, int featured) {

    }

    @Override
    public void updateWeight(long id, int weight) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void delete(long id, long authorId) {

    }

    @Override
    public void delete(Collection<Long> ids) {

    }

    @Override
    public void identityViews(long id) {
        // 次数不清理缓存, 等待文章缓存自动过期
        Post post = new Post();
        post.setId(id);
        post.setSteps(Consts.IDENTITY_STEP);
        postMapper.updateViews(post);
    }

    @Override
    public void identityComments(long id) {

    }

    @Override
    public void favor(long userId, long postId) {

    }

    @Override
    public void unfavor(long userId, long postId) {

    }

    @Override
    public long count() {
        return 0;
    }

    /**
     * 根据post填充PO
     * @param post
     * @param postVO
     */
    private void fillPostPO(Post post, PostVO postVO) {
        //文章内容
        Long postVOId = post.getId();
        PostAttribute postAttribute = postAttributeMapper.selectByPrimaryKey(postVOId);
        postVO.setContent(postAttribute.getContent());

        //获取作者
        Long authorId = post.getAuthorId();
        User user = userMapper.selectByPrimaryKey(authorId);
        UserVO userVO = BeanMapUtils.copy(user);
        postVO.setAuthor(userVO);

        //获取channel
        Integer postChannelId = post.getChannelId();
        List<Channel> channels = (List<Channel>) servletContext.getAttribute("channels");
        for(Channel channel : channels) {
            if(channel.getId()==postChannelId) {
                postVO.setChannel(channel);
                break;
            }
        }
    }
}