package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.web.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxm
 *
 */
@Controller
public class LogoutController extends BaseController {

	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		SecurityUtils.getSubject().logout();
		//发送一个报头，告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        /**
         * Expires实体报头域给出响应过期的日期和时间。
         * 为了让代理服务器或浏览器在一段时间以后更新缓存中
         * （再次访问曾访问过的页面时，直接从缓存中加载，缩短响应时间和降低服务器负载）的页面，
         * 我们可以使用Expires实体报头域指定页面过期时间。
         * 例：Expires:Thu,15 Sep 2006 16:23:12 GMT
         */
		//让浏览器不要缓存页面
        response.setDateHeader("Expires", 0); // Proxies.
		return "redirect:/index";
	}

}
