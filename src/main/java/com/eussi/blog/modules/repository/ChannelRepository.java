package com.eussi.blog.modules.repository;

import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Channel;

import java.util.Collection;
import java.util.List;

/**
 * @author wangxm
 *
 */
public interface ChannelRepository extends BaseRepository<Channel> {
	List<Channel> findAllByStatus(int status);
	List<Channel> findAllByIdIn(Collection<Integer> id);
}
