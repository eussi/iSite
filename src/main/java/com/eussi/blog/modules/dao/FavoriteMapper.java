package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Favorite;

public interface FavoriteMapper extends BaseMapper<Favorite>{

    /**
     * 指定查询
     *
     * @param ownId
     * @param postId
     * @return
     */
    Favorite findByOwnIdAndPostId(long ownId, long postId);

    void deleteByQuery(Favorite query);
}