package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Options;

public interface OptionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

    Options findByKey(String key);
}