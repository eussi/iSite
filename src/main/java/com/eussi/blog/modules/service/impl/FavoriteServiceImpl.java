package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.FavoriteMapper;
import com.eussi.blog.modules.po.Favorite;
import com.eussi.blog.modules.service.FavoriteService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.FavoriteVO;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = false)
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private PostService postService;

    @Override
    @Transactional
    public void add(long userId, long postId) {
        Favorite query = new Favorite();
        query.setOwnId(userId);
        query.setPostId(postId);
        List<Favorite> lists = favoriteMapper.findAllByQuery(query);

        Assert.isTrue(lists.size()<=0, "您已经收藏过此文章");

        // 如果没有喜欢过, 则添加记录
        Favorite po = new Favorite();
        po.setOwnId(userId);
        po.setPostId(postId);
        po.setCreated(new Date());

        favoriteMapper.insert(po);
    }

    @Override
    public void delete(long userId, long postId) {
        Favorite query = new Favorite();
        query.setOwnId(userId);
        query.setPostId(postId);
        List<Favorite> lists = favoriteMapper.findAllByQuery(query);

        Assert.notEmpty(lists, "还没有喜欢过此文章");

        favoriteMapper.deleteByQuery(query);
    }

    @Override
    public Page<FavoriteVO> pagingByOwnId(Page page, long ownId) {
        Favorite query = new Favorite();
        query.setOwnId(ownId);
        Long totalCount = favoriteMapper.getTotalCount(query);
        page.setTotalCount(totalCount);

        query.setOrderBy("  created desc");
        query.setLimit(page.getStartIndex() + "," + page.getPageSize());
        List<Favorite> list = favoriteMapper.findAllByQuery(query);

        List<FavoriteVO> rets = new ArrayList<>();
        Set<Long> postIds = new HashSet<>();
        for (Favorite po : list) {
            rets.add(BeanMapUtils.copy(po));
            postIds.add(po.getPostId());
        }

        if (postIds.size() > 0) {
            Map<Long, PostVO> posts = postService.findMapByIds(postIds);
            for (FavoriteVO t : rets) {
                PostVO p = posts.get(t.getPostId());
                t.setPost(p);
            }
        }

        page.setData(rets);
        return page;
    }

    @Override
    public Page<FavoriteVO> pageQuery(long currentNo, int pageSize, long ownId) {
        return null;
    }
}
