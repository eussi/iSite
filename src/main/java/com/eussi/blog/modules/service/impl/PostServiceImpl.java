package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.lang.EntityStatus;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.base.utils.DomainUtils;
import com.eussi.blog.base.utils.PreviewTextUtils;
import com.eussi.blog.modules.dao.ChannelMapper;
import com.eussi.blog.modules.dao.PostAttributeMapper;
import com.eussi.blog.modules.dao.PostMapper;
import com.eussi.blog.modules.dao.UserMapper;
import com.eussi.blog.modules.event.PostUpdateEvent;
import com.eussi.blog.modules.po.Channel;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.po.PostAttribute;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.service.ChannelService;
import com.eussi.blog.modules.service.FavoriteService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.modules.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

//    @Autowired
//    private ServletContext servletContext; //service层引用此对象，其他service层对象引用此对象会出现异常
    //找不到servletContext对象异常

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private FavoriteService favoriteService;

    @Override
    public Page<PostVO> paging(Page page, int channelId, Set<Integer> excludeChannelIds, String ord) {
        Post postQuery = new Post();

        if (channelId > Consts.ZERO) {
            postQuery.setChannelId(channelId);
        }

        postQuery.setNotIn(CommonUtils.concatInQuery("channel_id", excludeChannelIds, Consts.NOTIN));

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
        Post query = new Post();
        if (channelId > Consts.ZERO) {
            query.setChannelId(channelId);
        }

        if (StringUtils.isNotBlank(title)) {
            query.setMatch("title like '%".concat(title).concat("%'"));
        }

        if (id > Consts.ZERO) {
            query.setId(id);
        }

        //得到总记录数
        Long totalCount = postMapper.getTotalCount(query);
        page.setTotalCount(totalCount);

        //得到总页数
        page.setTotalPage(totalCount/page.getPageSize() + 1);

        query.setOrderBy(" weight desc, created desc");

        query.setLimit(page.getStartIndex() + "," + page.getPageSize());

        List<Post> queryResults = postMapper.findAllByQuery(query);

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
    public Page<PostVO> pagingByAuthorId(Page page, long userId) {
        Post postQuery = new Post();
        postQuery.setAuthorId(userId);

        //得到总记录数
        Long totalCount = postMapper.getTotalCount(postQuery);
        page.setTotalCount(totalCount);

        //得到总页数
        page.setTotalPage(totalCount/page.getPageSize() + 1);

        postQuery.setOrderBy(" created desc");

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
    public List<PostVO> findAllFeatured() {
        return null;
    }

    @Override
    public List<PostVO> findLatests(int maxResults, long ignoreUserId) {
        Post postQuery = new Post();

        postQuery.setOrderBy(" created desc");
        postQuery.setLimit("0," + maxResults);

        List<Post> queryResults = postMapper.findAllByQuery(postQuery);
        // 填充对象数据
        List<PostVO> results = new ArrayList<PostVO>();
        for(Post post : queryResults) {
            PostVO postVO = BeanMapUtils.copy(post);

            //文章内容
//            fillPostPO(post, postVO);

            results.add(postVO);
        }

        return results;
    }

    @Override
    public List<PostVO> findHots(int maxResults, long ignoreUserId) {
        Post postQuery = new Post();

        postQuery.setOrderBy(" views desc");
        postQuery.setLimit("0," + maxResults);

        List<Post> queryResults = postMapper.findAllByQuery(postQuery);
        // 填充对象数据
        List<PostVO> results = new ArrayList<PostVO>();
        for(Post post : queryResults) {
            PostVO postVO = BeanMapUtils.copy(post);

            //文章内容
//            fillPostPO(post, postVO);

            results.add(postVO);
        }

        return results;
    }

    @Override
    public Map<Long, PostVO> findMapByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }

        Post query = new Post();
        query.setIsIn(CommonUtils.concatInQuery("id", ids, Consts.IN));
        List<Post> list = postMapper.findAllByQuery(query);

        Map<Long, PostVO> rets = new HashMap<>();
        HashSet<Long> uids = new HashSet<>();

        for(Post po : list) {
            rets.put(po.getId(), BeanMapUtils.copy(po));
            uids.add(po.getAuthorId());
        }

        // 加载用户信息
        buildUsers(rets.values(), uids);

        return rets;
    }

    @Override
    public long post(PostVO post) {

        //新增
        DomainUtils.fillZero(post);//填充0

        Post po = new Post();
        BeanUtils.copyProperties(post, po);

        po.setCreated(new Date());
        po.setStatus(EntityStatus.ENABLED);

        // 处理摘要
        if (StringUtils.isBlank(post.getSummary())) {
            po.setSummary(trimSummary(post.getContent()));
        } else {
            po.setSummary(post.getSummary());
        }

        postMapper.insertAndGetId(po);

        PostAttribute attr = new PostAttribute();
        attr.setContent(post.getContent());
        attr.setId(po.getId());
        submitAttr(attr);

        onPushEvent(po, PostUpdateEvent.ACTION_PUBLISH);
        return po.getId();
    }

    private void submitAttr(PostAttribute attr) {
        postAttributeMapper.insert(attr);
    }

    private void updateAttr(PostAttribute attr) {
        postAttributeMapper.updateByPrimaryKeySelective(attr);
    }

    private void onPushEvent(Post post, int action) {
        PostUpdateEvent event = new PostUpdateEvent(System.currentTimeMillis());
        event.setPostId(post.getId());
        event.setUserId(post.getAuthorId());
        event.setAction(action);
        applicationContext.publishEvent(event);
    }

    /**
     * 截取文章内容
     * @param text
     * @return
     */
    private String trimSummary(String text){
        return PreviewTextUtils.getText(text, 126);
    }

    @Override
    public PostVO get(long id) {
        Post post = postMapper.selectByPrimaryKey(id);
        PostVO postVO = BeanMapUtils.copy(post);

        fillPostPO(post, postVO);

        return postVO;
    }

    /**
     * 文章更新
     * @param post
     */
    @Override
    public void update(PostVO post) {
        Post po = postMapper.selectByPrimaryKey(post.getId());
        if(po!=null) {
            po.setTitle(post.getTitle());//标题
            po.setChannelId(post.getChannelId());
            po.setThumbnail(post.getThumbnail());
            // 处理摘要
            if (StringUtils.isBlank(post.getSummary())) {
                po.setSummary(trimSummary(post.getContent()));
            } else {
                po.setSummary(post.getSummary());
            }

            po.setTags(post.getTags());

            //更新文章信息
            postMapper.updateByPrimaryKeySelective(po);

            //更新文章内容
            PostAttribute attr = new PostAttribute();
            attr.setContent(post.getContent());
            attr.setId(po.getId());
            updateAttr(attr);
        }
    }



    @Override
    public void updateFeatured(long id, int featured) {
        Post po = postMapper.selectByPrimaryKey(id);
        int status = Consts.FEATURED_ACTIVE == featured ? Consts.FEATURED_ACTIVE: Consts.FEATURED_DEFAULT;
        po.setFeatured(status);
        postMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void updateWeight(long id, int weight) {
        Post po = postMapper.selectByPrimaryKey(id);

        int max = weight;
        //推荐，则将weight置为最大值
        if (Consts.FEATURED_ACTIVE == weight) {
            max = postMapper.maxWeight() + 1;
        }
        po.setWeight(max);
        postMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void delete(long id) {
        Post po = postMapper.selectByPrimaryKey(id);
        if(po!=null) {
            postMapper.deleteByPrimaryKey(id);
            postAttributeMapper.deleteByPrimaryKey(id);

            onPushEvent(po, PostUpdateEvent.ACTION_DELETE);
        }
    }

    @Override
    public void delete(long id, long authorId) {
        Post po = postMapper.selectByPrimaryKey(id);
        if(po!=null) {
            // 判断文章是否属于当前登录用户
            Assert.isTrue(po.getAuthorId() == authorId, "认证失败");

            postMapper.deleteByPrimaryKey(id);
            postAttributeMapper.deleteByPrimaryKey(id);

            onPushEvent(po, PostUpdateEvent.ACTION_DELETE);

        }

    }

    @Override
    public void delete(Collection<Long> ids) {
        if(ids!=null && ids.size()>0) {
            for(long id : ids) {
                delete(id);
            }
        }
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
    public void identityComments(long id, boolean addOne) {
        Post post = new Post();
        post.setId(id);
        if(addOne) {
            post.setSteps(Consts.IDENTITY_STEP);
        } else {
            post.setSteps(Consts.DECREASE_STEP);
        }
        postMapper.updateComments(post);
    }

    @Override
    public void favor(long userId, long postId) {
        Post post = new Post();
        post.setId(postId);
        post.setSteps(Consts.IDENTITY_STEP);

        //事务未生效，问题待解决，这里先做添加操作，添加失败，再更新文章
        favoriteService.add(userId, postId);
        postMapper.updateFavors(post);
    }

    @Override
    public void unfavor(long userId, long postId) {
        Post post = new Post();
        post.setId(postId);
        post.setSteps(Consts.DECREASE_STEP);

        //事务未生效，问题待解决，这里先做添加操作，添加失败，再更新文章
        favoriteService.delete(userId, postId);
        postMapper.updateFavors(post);
    }

    @Override
    public long count() {
        return postMapper.getTotalCount(new Post());
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
//        List<Channel> channels = (List<Channel>) servletContext.getAttribute("channels");
        List<Channel> channels = channelService.findAll(Consts.STATUS_NORMAL);
        for(Channel channel : channels) {
            if(channel.getId()==postChannelId) {
                postVO.setChannel(channel);
                break;
            }
        }
    }

    //加载用户信息
    private void buildUsers(Collection<PostVO> posts, Set<Long> uids) {
        Map<Long, UserVO> userMap = userService.findMapByIds(uids);
        for(PostVO postVO : posts) {
            postVO.setAuthor(userMap.get(postVO.getAuthorId()));
        }
    }
}
