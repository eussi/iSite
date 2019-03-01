package com.eussi.blog.modules.dao;


import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Message;

public interface MessageMapper extends BaseMapper<Message>{

    int countByOwnIdAndUnread(Message message);

    void updateByOwnId(Message update);
}