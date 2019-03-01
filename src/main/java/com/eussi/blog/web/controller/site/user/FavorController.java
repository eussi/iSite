package com.eussi.blog.web.controller.site.user;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.event.MessageEvent;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxm on 2019/2/28.
 */
@RestController
@RequestMapping("/user")
public class FavorController extends BaseController {
    @Autowired
    private PostService postService;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 收藏文章
     * @param id
     * @return
     */
    @RequestMapping("/favor")
    public Data favor(Long id) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                AccountProfile up = getProfile();
                postService.favor(up.getId(), id);

                sendNotify(up.getId(), id);

                data = Data.success("操作成功!", Data.NOOP);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }

    /**
     * 取消喜欢文章
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/unfavor")
    public Data unfavor(Long id, HttpServletRequest request) {
        Data data = Data.failure("操作失败");
        if (id != null) {
            try {
                AccountProfile up = getProfile();
                postService.unfavor(up.getId(), id);
                data = Data.success("操作成功!", Data.NOOP);
            } catch (Exception e) {
                data = Data.failure(e.getMessage());
            }
        }
        return data;
    }

    /**
     * 发送通知
     * @param userId
     * @param postId
     */
    private void sendNotify(long userId, long postId) {
        MessageEvent event = new MessageEvent("MessageEvent");
        event.setFromUserId(userId);
        event.setEvent(Consts.MESSAGE_EVENT_FAVOR_POST);
        // 此处不知道文章作者, 让通知事件系统补全
        event.setPostId(postId);
        applicationContext.publishEvent(event);
    }
}
