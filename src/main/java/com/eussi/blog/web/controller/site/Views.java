package com.eussi.blog.web.controller.site;

/**
 * 
 * 返回页面配置
 * 
 * @author wangxm
 *
 */
public interface Views {
    String INDEX = "/index";

    String LOGIN = "/auth/login";
    String REGISTER = "/auth/register";
    String REGISTER_RESULT = "/auth/register_result";

	String OAUTH_REGISTER = "/auth/oauth_register";

	String FORGOT = "/auth/forgot";

	String USER_POSTS = "/user/method_posts";
	String USER_COMMENTS = "/user/method_comments";
	String USER_FAVORS = "/user/method_favors";
	String USER_MESSAGES = "/user/method_messages";

    String REDIRECT_INDEX = "redirect:/index";
	String REDIRECT_USER = "redirect:/user";
	String REDIRECT_USER_POSTS = "redirect:/user?method=posts";

	String USER_AVATAR = "/user/avatar";
	String USER_PASSWORD = "/user/password";
	String USER_PROFILE = "/user/profile";
	String USER_EMAIL = "/user/email";

	String USERS_VIEW = "/users/view";

	String TAGS_TAG = "/tag";
	
	String BROWSE_SEARCH = "/search";

	String ROUTE_POST_EDITING = "/channel/editing";
	String ROUTE_POST_INDEX = "/channel/index";
	String ROUTE_POST_VIEW = "/channel/view";
}
