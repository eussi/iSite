package com.eussi.blog.web.controller.site.api;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.modules.service.CommentService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.CommentVO;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 侧边栏数据加载
 * 
 * @author wangxm
 *
 */
@Controller
@RequestMapping("/api")
public class SidebarController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
    Data login(HttpServletRequest request, String username, String password,  String imagecode, ModelMap model) {
		Data data = Data.failure("操作失败");

        String imagecodeSession = (String) request.getSession().getAttribute("imagecode");
        if(imagecodeSession == null || !imagecodeSession.equalsIgnoreCase(imagecode)) {
            return Data.failure("验证码输入错误");
        }

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return Data.failure("用户名或者密码输入为空");
		}

		AuthenticationToken token = createToken(username, password);
		if (token == null) {
			data.setMessage("用户名或密码错误");
			return data;
		}

		try {
			SecurityUtils.getSubject().login(token);
			data = Data.success("登录成功", getProfile());

		} catch (Exception e) {
			if (e instanceof UnknownAccountException) {
				data.setMessage("用户不存在");
			} else if (e instanceof LockedAccountException) {
				data.setMessage("用户被禁用");
			} else {
				data.setMessage("登录认证失败");
			}
		}
		return data;
	}

	@RequestMapping("/latests")
	public @ResponseBody List<PostVO> latests() {
		AccountProfile up = getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<PostVO> rets = postService.findLatests(6, ignoreUserId);
		return rets;
	}
	
	@RequestMapping("/hots")
	public @ResponseBody List<PostVO> hots() {
		AccountProfile up = getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<PostVO> rets = postService.findHots(6, ignoreUserId);
		return rets;
	}
	
	/**
	 * 热门评论
	 * @return
	 */
	@RequestMapping(value="/latest_comments")
	public @ResponseBody List<CommentVO> latestComments() {
         return commentService.latests(6);
	}
}
