package com.eussi.blog.modules.event.handler;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.event.MessageEvent;
import com.eussi.blog.modules.service.MessageService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.MessageVO;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wangxm on 2019/2/25.
 */
@Component
public class MessageEventHandler implements ApplicationListener<MessageEvent> {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;

    @Async
    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageVO nt = new MessageVO();
        nt.setPostId(event.getPostId());
        nt.setFromId(event.getFromUserId());
        nt.setEvent(event.getEvent());

        switch (event.getEvent()) {
            case Consts.MESSAGE_EVENT_FAVOR_POST:
                PostVO p = postService.get(event.getPostId());
                nt.setOwnId(p.getAuthorId());
                break;
            case Consts.MESSAGE_EVENT_COMMENT:
            case Consts.MESSAGE_EVENT_COMMENT_REPLY:
                PostVO p2 = postService.get(event.getPostId());
                nt.setOwnId(p2.getAuthorId());

                // 自增评论数
                postService.identityComments(event.getPostId(), true);
                break;
            default:
                nt.setOwnId(event.getToUserId());
        }

        messageService.send(nt);
    }
}
