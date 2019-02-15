package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.MessageMapper;
import com.eussi.blog.modules.service.MessageService;
import com.eussi.blog.modules.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxueming on 2019/2/15.
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Page<MessageVO> findByOwnId(Page page, long ownId) {
        return null;
    }

    @Override
    public void send(MessageVO notify) {

    }

    @Override
    public int unread4Me(long ownId) {
        return messageMapper.countByOwnIdAndUnread(ownId);
    }

    @Override
    public void readed4Me(long ownId) {

    }
}
