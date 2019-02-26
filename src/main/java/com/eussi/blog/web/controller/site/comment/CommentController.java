package com.eussi.blog.web.controller.site.comment;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.event.MessageEvent;
import com.eussi.blog.modules.service.CommentService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.CommentVO;
import com.eussi.blog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxm
 *
 */
@Controller
@RequestMapping("/comment")
@ConditionalOnProperty(name = "site.controls.comment", havingValue = "true", matchIfMissing = true)
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping("/list/{toId}")
	public @ResponseBody Page<CommentVO> view(@PathVariable Long toId) {
		Page pageable = wrapPage();
		Page<CommentVO> page = commentService.paging(pageable, toId);
		return page;
	}
	
	@RequestMapping("/submit")
	public @ResponseBody
    Data post(Long toId, String text, HttpServletRequest request) {
		Data data = Data.failure("操作失败");
		
		long pid = ServletRequestUtils.getLongParameter(request, "pid", 0);
		
		if (!isAuthenticated()) {
			data = Data.failure("请先登录在进行操作");
			return data;
		}
        //toId是文章Id
		if (toId > 0 && StringUtils.isNotEmpty(text)) {
			AccountProfile up = getProfile();
			
			CommentVO c = new CommentVO();
			c.setToId(toId);
			c.setContent(HtmlUtils.htmlEscape(text));
			c.setAuthorId(up.getId());
			c.setPid(pid);
			commentService.post(c);

            //如果被评论用户不是本用户，发送通知
//            if(toId != up.getId()) {
			    sendNotify(up.getId(), toId, pid);//未实现功能，回复了某人，某人需要收到通知
//            }
			
			data = Data.success("发表成功!", Data.NOOP);
		}
		return data;
	}

	@RequestMapping("/delete")
	public @ResponseBody Data delete(Long id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			AccountProfile up = getProfile();
			try {
				commentService.delete(id, up.getId());
				data = Data.success("操作成功", Data.NOOP);
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
	private void sendNotify(long userId, long postId, long pid) {
		MessageEvent event = new MessageEvent("MessageEvent");
		event.setFromUserId(userId);

		if (pid > 0) {
			event.setEvent(Consts.MESSAGE_EVENT_COMMENT_REPLY);
		} else {
			event.setEvent(Consts.MESSAGE_EVENT_COMMENT);
		}
		// 此处未查询文章作者, 需要通知事件系统补全
		event.setPostId(postId);
		applicationContext.publishEvent(event);
	}
}