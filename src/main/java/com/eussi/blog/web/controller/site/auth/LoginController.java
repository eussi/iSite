package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录页
 * @author wangxm
 */
@Slf4j
@Controller
public class LoginController extends BaseController {
    /**
     * 跳转登录页
     * @return
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String view() {
		return view(Views.LOGIN);
	}

    /**
     * 提交登录
     * @param username
     * @param password
     * @param model
     * @return
     */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String username, String password, String imagecode, @RequestParam(value = "rememberMe",defaultValue = "0") int rememberMe, ModelMap model) {
        String imagecodeSession = (String) request.getSession().getAttribute("imagecode");
        Assert.isTrue(imagecodeSession != null && imagecodeSession.equalsIgnoreCase(imagecode), "验证码输入错误");

        String ret = view(Views.LOGIN);
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ret;
        }

        UsernamePasswordToken token = createToken(username, password);
        if (token == null) {
        	model.put("message", "用户名或密码错误");
            return ret;
        }

        if (rememberMe == 1) {
            token.setRememberMe(true);
        }

        try {
            SecurityUtils.getSubject().login(token);
            ret = Views.REDIRECT_USER;
        } catch (UnknownAccountException e) {
            log.error(e.getMessage());
            throw new BlogException("用户不存在");
        } catch (LockedAccountException e) {
            log.error(e.getMessage());
            throw new BlogException("用户被禁用");
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new BlogException("用户认证失败");
        }
        return ret;
	}

}
