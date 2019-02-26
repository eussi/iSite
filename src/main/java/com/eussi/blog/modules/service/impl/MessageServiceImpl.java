package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.MessageMapper;
import com.eussi.blog.modules.po.Message;
import com.eussi.blog.modules.service.MessageService;
import com.eussi.blog.modules.vo.MessageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        if (notify == null || notify.getOwnId() <=0 || notify.getFromId() <= 0) {
            return;
        }

        Message po = new Message();
        BeanUtils.copyProperties(notify, po);
        po.setCreated(new Date());
        po.setStatus(Consts.UNREAD);

        messageMapper.insert(po);
    }

    @Override
    public int unread4Me(long ownId) {
        Message message = new Message();
        message.setOwnId(ownId);
        message.setStatus(Consts.UNREAD);
        return messageMapper.countByOwnIdAndUnread(message);
    }

    @Override
    public void readed4Me(long ownId) {

    }
}
