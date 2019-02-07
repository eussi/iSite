package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.FavoriteService;
import com.eussi.blog.modules.vo.FavoriteVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class FavoriteServiceImpl implements FavoriteService {
    @Override
    public void add(long userId, long postId) {

    }

    @Override
    public void delete(long userId, long postId) {

    }

    @Override
    public Page<FavoriteVO> pageQuery(long currentNo, int pageSize, long ownId) {
        return null;
    }
}
