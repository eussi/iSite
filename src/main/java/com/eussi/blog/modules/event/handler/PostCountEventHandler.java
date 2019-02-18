package com.eussi.blog.modules.event.handler;

import com.eussi.blog.modules.event.PostUpdateEvent;
import com.eussi.blog.modules.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 更新文章统计
 * created by wangxm
 */
@Component
public class PostCountEventHandler implements ApplicationListener<PostUpdateEvent> {
    @Autowired
    private UserEventService userEventService;

    @Async
    @Override
    public void onApplicationEvent(PostUpdateEvent event) {
        if (event == null) {
            return;
        }

        switch (event.getAction()) {
            case PostUpdateEvent.ACTION_PUBLISH:
                userEventService.identityPost(event.getUserId(), event.getPostId());
                break;
            case PostUpdateEvent.ACTION_DELETE:
                //需要减一
//                userEventService.identityPost(event.getUserId(), event.getPostId());
                break;
        }
    }
}
