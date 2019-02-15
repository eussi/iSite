package com.eussi.blog.web.controller.site.user;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.*;
import com.eussi.blog.modules.vo.*;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户主页
 * @author wangxm
 *
 */
@Controller
public class UserController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private MessageService messageService;

	/**
	 * 我发布的文章
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user")
	public String posts(ModelMap model) {
		Page pageable = wrapPage();
		Page<PostVO> page = postService.pagingByAuthorId(pageable, getProfile().getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_POSTS);
	}

	/**
	 * 我发表的评论
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=comments")
	public String comments(ModelMap model) {
		Page pageable = wrapPage();
		AccountProfile profile = getProfile();
		Page<CommentVO> page = commentService.paging4Home(pageable, profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_COMMENTS);
	}

	/**
	 * 我喜欢过的文章
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=favors")
	public String favors(ModelMap model) {
		Page pageable = wrapPage();
		AccountProfile profile = getProfile();
		Page<FavoriteVO> page = favoriteService.pagingByOwnId(pageable, profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_FAVORS);
	}

	/**
	 * 我的通知
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=messages")
	public String notifies(ModelMap model) {
		Page pageable = wrapPage();
		AccountProfile profile = getProfile();
		Page<MessageVO> page = messageService.findByOwnId(pageable, profile.getId());
		// 标记已读
		messageService.readed4Me(profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_MESSAGES);
	}

	private void initUser(ModelMap model) {
		AccountProfile up = getProfile();
		UserVO user = userService.get(up.getId());

		model.put("user", user);

		pushBadgesCount();
	}

	private void pushBadgesCount() {
		AccountProfile profile = (AccountProfile) session.getAttribute("profile");
		if (profile != null && profile.getBadgesCount() != null) {
			BadgesCount count = new BadgesCount();
			count.setMessages(messageService.unread4Me(profile.getId()));
			profile.setBadgesCount(count);
			session.setAttribute("profile", profile);
		}
	}

}
