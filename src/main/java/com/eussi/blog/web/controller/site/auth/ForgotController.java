package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.MailHelper;
import com.eussi.blog.modules.service.SecurityCodeService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.UserVO;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wangxmx on 2019/3/6.
 */
@Controller
public class ForgotController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;
    @Autowired
    private MailHelper mailHelper;

    @GetMapping("/forgot")
    public String view(ModelMap model) {
        if(true) {
            model.put("data",Data.failure("暂不支持的操作，请见谅！").addLink("index", "返回首页"));
            return view(Views.REGISTER_RESULT);
        }

        return view(Views.FORGOT);
    }

    @PostMapping("/forgot")
    public String reset(String email, String code, String password, ModelMap model) {
        Data data;

        try {
            Assert.hasLength(email, "请输入邮箱地址");
            Assert.hasLength(code, "请输入验证码");
            UserVO user = userService.getByEmail(email);
            Assert.notNull(user, "账户不存在");

            securityCodeService.verify(user.getId(), Consts.VERIFY_FORGOT, code);
            userService.updatePassword(user.getId(), password);

            data = Data.success("恭喜您! 密码重置成功。");
            data.addLink("login", "前往登录");

        } catch (Exception e) {
            data = Data.failure(e.getMessage());
        }

        model.put("data", data);
        return view(Views.REGISTER_RESULT);
    }
}
