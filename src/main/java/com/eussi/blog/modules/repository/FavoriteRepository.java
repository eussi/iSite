package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Favorite;

/**
 * @author wangxm
 */
public interface FavoriteRepository extends BaseRepository<Favorite> {
    /**
     * 指定查询
     *
     * @param ownId
     * @param postId
     * @return
     */
    Favorite findByOwnIdAndPostId(long ownId, long postId);
}
