package com.eussi.blog.modules.service;


import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.vo.MessageVO;

/**
 * @author wangxm on 2019/2/15.
 */
public interface MessageService {

    Page<MessageVO> findByOwnId(Page page, long ownId);

    /**
     * 发送通知
     * @param notify
     */
    void send(MessageVO notify);

    /**
     * 未读消息数量
     * @param ownId
     * @return
     */
    int unread4Me(long ownId);

    /**
     * 标记为已读
     * @param ownId
     */
    void readed4Me(long ownId);

}
