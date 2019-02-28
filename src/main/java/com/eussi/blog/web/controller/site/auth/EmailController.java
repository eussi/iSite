package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.service.SecurityCodeService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.UserVO;
import com.eussi.blog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxm on 2019/2/28.
 */
@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;

    @GetMapping("/send_code")
    @ResponseBody
    public Data sendCode(String email, Integer type) {
        Assert.notNull(email, "请输入邮箱地址");
        Assert.notNull(type, "缺少必要的参数");

        long userId;

        if (Consts.VERIFY_FORGOT == type) {
            UserVO user = userService.getByEmail(email);
            Assert.notNull(user, "账户不存在");
            userId = user.getId();
        } else {
            AccountProfile profile = getProfile();
            Assert.notNull(profile, "请先登录后再进行此操作");
            userId = profile.getId();
        }

        String code = securityCodeService.generateCode(userId, type, email);
        Map<String, Object> context = new HashMap<>();
        context.put("code", code);

        sendEmail(Consts.EMAIL_TEMPLATE_CODE, email, "邮箱验证码", context);
        return Data.success("邮件发送成功", Data.NOOP);
    }

}
