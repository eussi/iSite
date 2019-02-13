package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.dao.ChannelMapper;
import com.eussi.blog.modules.po.Channel;
import com.eussi.blog.modules.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;
    @Override
    public List<Channel> findAll(int status) {
        return channelMapper.findAllByStatus(status);
    }

    @Override
    public Map<Integer, Channel> findMapByIds(Collection<Integer> ids) {
        return null;
    }

    @Override
    public Channel getById(int id) {
        return null;
    }

    @Override
    public void update(Channel channel) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public long count() {
        return 0;
    }
}
