package com.eussi.blog.modules.dao;

import com.eussi.blog.modules.po.Channel;

import java.util.Collection;
import java.util.List;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

    List<Channel> findAllByStatus(int status);

    List<Channel> findAllByIdIn(Collection<Integer> id);
}