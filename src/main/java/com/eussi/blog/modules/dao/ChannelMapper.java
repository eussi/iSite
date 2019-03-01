package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Channel;

import java.util.Collection;
import java.util.List;

public interface ChannelMapper extends BaseMapper<Channel>{

    List<Channel> findAllByStatus(int status);

    List<Channel> findAllByIdIn(Collection<Integer> id);
}