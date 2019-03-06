package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.PostSearchService;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class PostSearchServiceImpl implements PostSearchService {
    @Override
    public Page<PostVO> search(Page page, String q) throws Exception {
        List<PostVO> data = new ArrayList<PostVO>();
        //deal 暂时未实现
        page.setTotalCount(0);

        page.setData(data);
        return page;
    }

    @Override
    public Page<PostVO> searchByTag(Page page, String tag) {
        List<PostVO> data = new ArrayList<PostVO>();
        //deal 暂时未实现
        page.setTotalCount(0);

        page.setData(data);
        return page;
    }

    @Override
    public void resetIndexs() {

    }
}
