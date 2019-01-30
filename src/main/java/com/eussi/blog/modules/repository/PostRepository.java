package com.eussi.blog.modules.repository;


import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Post;

import java.util.Collection;
import java.util.List;

/**
 * @author wangxm
 */
public interface PostRepository extends BaseRepository<Post> {

    // findLatests
    List<Post> findTop10ByOrderByCreatedDesc();

    // findHots
    List<Post> findTop10ByOrderByViewsDesc();

    List<Post> findAllByIdIn(Collection<Long> id);

    List<Post> findTop5ByFeaturedGreaterThanOrderByCreatedDesc(int featured);

    int maxWeight();


}
