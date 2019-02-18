package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Post;

import java.util.Collection;
import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    /**
     * 根据查询条件获取总页数
     * @param postQuery
     * @return
     */
    Long getTotalCount(Post postQuery);

    List<Post> findAllByQuery(Post postQuery);

    void updateViews(Post post);

    // findLatests
    List<Post> findTop10ByOrderByCreatedDesc();

    // findHots
    List<Post> findTop10ByOrderByViewsDesc();

    List<Post> findAllByIdIn(Collection<Long> id);

    List<Post> findTop5ByFeaturedGreaterThanOrderByCreatedDesc(int featured);

    int maxWeight();


    Long insertAndGetId(Post po);
}