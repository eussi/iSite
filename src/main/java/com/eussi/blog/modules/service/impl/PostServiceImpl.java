package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    @Override
    public Page<PostVO> paging(Page page, int channelId, Set<Integer> excludeChannelIds, String ord) {
        return null;
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
        return null;
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
}
