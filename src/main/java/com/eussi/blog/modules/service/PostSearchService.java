package com.eussi.blog.modules.service;


import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.vo.PostVO;

/**
 * @author : wangxm
 */
public interface PostSearchService {
    /**
     * 根据关键字搜索
     * @param page
     * @param q
     * @throws Exception
     */
    Page<PostVO> search(Page page, String q) throws Exception;

    /**
     * 搜索 Tag
     * @param page
     * @param tag
     */
    Page<PostVO> searchByTag(Page page, String tag);
    void resetIndexs();
}
