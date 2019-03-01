package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Post;

import java.util.Collection;
import java.util.List;

public interface PostMapper extends BaseMapper<Post>{

    void updateViews(Post post);

    // findLatests
    List<Post> findTop10ByOrderByCreatedDesc();

    // findHots
    List<Post> findTop10ByOrderByViewsDesc();

    List<Post> findAllByIdIn(Collection<Long> id);

    List<Post> findTop5ByFeaturedGreaterThanOrderByCreatedDesc(int featured);

    int maxWeight();


    Long insertAndGetId(Post po);

    void updateComments(Post post);

    void updateFavors(Post post);
}