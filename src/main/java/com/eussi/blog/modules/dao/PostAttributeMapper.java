package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.PostAttribute;

public interface PostAttributeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PostAttribute record);

    int insertSelective(PostAttribute record);

    PostAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostAttribute record);

    int updateByPrimaryKeyWithBLOBs(PostAttribute record);
}