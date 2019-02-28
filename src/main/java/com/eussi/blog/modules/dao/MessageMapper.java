package com.eussi.blog.modules.dao;


import com.eussi.blog.modules.po.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    int countByOwnIdAndUnread(Message message);

    Long getTotalCount(Message query);

    List<Message> findAllByQuery(Message query);

    void updateByOwnId(Message update);
}