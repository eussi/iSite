package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Favorite;

public interface FavoriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    /**
     * 指定查询
     *
     * @param ownId
     * @param postId
     * @return
     */
    Favorite findByOwnIdAndPostId(long ownId, long postId);
}