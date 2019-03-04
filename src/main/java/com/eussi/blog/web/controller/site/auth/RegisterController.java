package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.DomainUtils;
import com.eussi.blog.base.utils.ValidateCodeUtils;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.UserVO;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author wangxm
 *
 */
@Controller
@ConditionalOnProperty(name = "site.controls.register", havingValue = "true", matchIfMissing = true)
public class RegisterController extends BaseController {
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String view() {
		AccountProfile profile = getProfile();
		if (profile != null) {
			return view(Views.REDIRECT_INDEX);
		}
		return view(Views.REGISTER);
	}
	
	@PostMapping("/register")
	public String register(HttpServletRequest request, UserVO post, ModelMap model) {
        String imagecode = (String) request.getSession().getAttribute("imagecode");

        Assert.isTrue(imagecode != null && imagecode.equals(post.getImagecode()), "验证码输入错误");

		Data data;
		String ret = view(Views.REGISTER);

		try {
			post.setAvatar(Consts.AVATAR);
            DomainUtils.fillZero(post);
			userService.register(post);
			data = Data.success("恭喜您! 注册成功", Data.NOOP);
			data.addLink("login", "前往登录");
			ret = view(Views.REGISTER_RESULT);
		} catch (Exception e) {
            model.addAttribute("post", post);
			data = Data.failure(e.getMessage());
		}
		model.put("data", data);
		return ret;
	}

    //生成验证码
    @RequestMapping("/register/validate")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        BufferedImage image = new BufferedImage(ValidateCodeUtils.WIDTH,ValidateCodeUtils.HEIGHT,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        //1.设置背景色
        ValidateCodeUtils.setBackGround(g);

        //2.设置边框
        ValidateCodeUtils.SetBorder(g);

        //3.画干扰线
        ValidateCodeUtils.drawRandomLine(g);

        //4.写随机数
        String random = ValidateCodeUtils.drawRandomNum((Graphics2D) g);
        request.getSession().setAttribute("imagecode", random);

        //5.图形写给浏览器
        response.setContentType("image/jpeg");
        //发头控制浏览器不要缓存
        //刷新按钮，不管有没有缓存都会向服务器请求，把上次的事情再做一次，与回车有所区别
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image, "jpg", response.getOutputStream());
        return null;
    }

}