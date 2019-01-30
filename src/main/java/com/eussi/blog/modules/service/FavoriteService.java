package com.eussi.blog.modules.service;


import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.vo.FavoriteVO;

import java.util.Map;

/**
 * @author wangxm on 2019/1/30.
 */
public interface FavoriteService {
    /**
     *
     * @param userId
     * @param postId
     * @return
     */
    void add(long userId, long postId);
    void delete(long userId, long postId);


//    Page<FavoriteVO> pagingByOwnId(Pageable pageable, long ownId);
    /**
     * 分页查询用户的喜欢记录
     * @param currentNo
     * @param pageSize
     * @param ownId
     * @return
     */
    public Page<FavoriteVO> pageQuery(long currentNo, int pageSize, long ownId);
}
