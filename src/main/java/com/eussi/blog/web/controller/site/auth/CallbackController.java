package com.eussi.blog.web.controller.site.auth;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.oauth.*;
import com.eussi.blog.base.oauth.utils.OpenOauthBean;
import com.eussi.blog.base.oauth.utils.TokenUtil;
import com.eussi.blog.base.utils.FilePathUtils;
import com.eussi.blog.base.utils.ImageUtils;
import com.eussi.blog.modules.service.OpenOauthService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.OpenOauthVO;
import com.eussi.blog.modules.vo.UserVO;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 第三方登录回调
 *
 * @author wangxm on 2019/3/6.
 */
@Slf4j
@Controller
@RequestMapping("/oauth/callback")
@ConditionalOnProperty(name = "site.controls.register", havingValue = "true", matchIfMissing = true)
public class CallbackController extends BaseController {
    private static final String SESSION_STATE = "_SESSION_STATE_";

    @Autowired
    private OpenOauthService openOauthService;
    @Autowired
    private UserService userService;

    /**
     * 跳转到微博进行授权
     *
     * @param request
     * @param response
     */
    @RequestMapping("/call_weibo")
    public void callWeibo(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            //需要的一些基本信息
            APIConfig.getInstance().setOpenid_sina(siteOptions.getOptions().get(Consts.WEIBO_CLIENT_ID));
            APIConfig.getInstance().setOpenkey_sina(siteOptions.getOptions().get(Consts.WEIBO_CLIENT_SERCRET));
            APIConfig.getInstance().setRedirect_sina(siteOptions.getOptions().get(Consts.WEIBO_CALLBACK));

            //state会被回传
            // 用于保持请求和回调的状态，在回调时，会在Query Parameter中回传该参数。
            // 开发者可以用这个参数验证请求有效性，也可以记录用户请求授权页前的位置。
            // 这个参数可用于防止跨站请求伪造（CSRF）攻击
            String state = TokenUtil.randomState();//获取24位长度字符串
            request.getSession().setAttribute(SESSION_STATE, state);

            //首先需要传入必要参数：client_id和redirect_uri，构造请求授权页URL
            response.sendRedirect(OauthSina.me().getAuthorizeUrl(state));
        } catch (Exception e) {
            throw new BlogException("跳转到微博授权接口时发生异常");
        }
    }

    /**
     * 微博回调
     *
     * @param code
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/weibo")
    public String callback4Weibo(String code, String state, HttpServletRequest request, ModelMap model) throws Exception {
        // 获取sesion中的state
        String session_state = (String) request.getSession().getAttribute(SESSION_STATE);
        //验证是否是合法请求
        if (StringUtils.isBlank(state) || StringUtils.isBlank(session_state) || !state.equals(session_state) || StringUtils.isBlank(code)) {
            throw new BlogException("请求无效！");
        }
        //移除设置的state
        request.getSession().removeAttribute(SESSION_STATE);

        OpenOauthBean openOauthBean = null;
        try {
            //根据返回结果中的Code，调用oauth2/access_token接口，获取授权后的access token
            openOauthBean = OauthSina.me().getUserBeanByCode(code);
        } catch (Exception e) {
            throw new BlogException("微博登录失败，解析信息时发生错误");
        }

        OpenOauthVO openOauth = new OpenOauthVO();
        openOauth.setOauthCode(code);
        openOauth.setAccessToken(openOauthBean.getAccessToken());
        openOauth.setExpireIn(openOauthBean.getExpireIn());
        openOauth.setOauthUserId(openOauthBean.getOauthUserId());//用户UID
        openOauth.setOauthType(openOauthBean.getOauthType());
        openOauth.setRefreshToken(openOauthBean.getRefreshToken());
        openOauth.setUsername(openOauthBean.getUsername());
        openOauth.setNickname(openOauthBean.getNickname());
        openOauth.setAvatar(openOauthBean.getAvatar());

        // 判断是否存在绑定此accessToken的用户
        OpenOauthVO thirdToken = openOauthService.getOauthByOauthUserId(openOauth.getOauthUserId());
        if (thirdToken == null) {
            model.put("open", openOauth);
            return view(Views.OAUTH_REGISTER);
        }
        String username = userService.get(thirdToken.getUserId()).getUsername();
        return login(username, thirdToken.getAccessToken(), request);
    }



    /**
     * 执行第三方注册绑定
     *
     * @param openOauth
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/bind_oauth")
    public String bindOauth(OpenOauthVO openOauth, HttpServletRequest request) throws Exception {
        OpenOauthVO thirdToken = openOauthService.getOauthByOauthUserId(openOauth.getOauthUserId());
        String username = openOauth.getUsername();

        // 已存在：提取用户信息，登录
        if (thirdToken != null) {
            username = userService.get(thirdToken.getUserId()).getUsername();
            // 不存在：注册新用户，并绑定此token，登录
        } else {
            UserVO user = userService.getByUsername(username);
            if (user == null) {
                UserVO u = userService.register(wrapUser(openOauth));

                // ===将远程图片下载到本地===
                String ava100 = Consts.avatarPath + getAvaPath(u.getId(), 100);
                byte[] bytes = ImageUtils.download(openOauth.getAvatar());
                storageFactory.get().writeToStore(bytes, ava100);
                userService.updateAvatar(u.getId(), ava100);

                thirdToken = new OpenOauthVO();
                BeanUtils.copyProperties(openOauth, thirdToken);
                thirdToken.setUserId(u.getId());
                openOauthService.saveOauthToken(thirdToken);
            } else {
                username = user.getUsername();
            }
        }
        return login(username, openOauth.getAccessToken(), request);
    }

    /**
     * 执行登录请求
     *
     * @param username
     * @param request
     * @return
     */
    private String login(String username, String accessToken, HttpServletRequest request) {
        String ret = view(Views.LOGIN);

        if (StringUtils.isNotBlank(username)) {
            UsernamePasswordToken token = createToken(username, accessToken);
            try {
                SecurityUtils.getSubject().login(token);
                ret = Views.REDIRECT_USER;
            } catch (UnknownAccountException e) {
                throw new BlogException("用户不存在");
            } catch (LockedAccountException e) {
                throw new BlogException("用户被禁用");
            } catch (AuthenticationException e) {
                log.error(e.getMessage());
                throw new BlogException("用户认证失败");
            }
            return ret;
        }
        throw new BlogException("登录失败！");
    }

    private UserVO wrapUser(OpenOauthVO openOauth) {
        //将openOauth放入UserVO
        UserVO user = new UserVO();
        user.setUsername(openOauth.getUsername());
        user.setName(openOauth.getNickname());
        user.setPassword(openOauth.getAccessToken());

        //头像处理
        if (StringUtils.isNotBlank(openOauth.getAvatar())) {
            //FIXME: 这里使用网络路径，前端应做对应处理
            user.setAvatar(openOauth.getAvatar());
        } else {
            user.setAvatar(Consts.AVATAR);
        }
        return user;
    }

    public String getAvaPath(long uid, int size) {
        String base = FilePathUtils.getAvatar(uid);
        return String.format("/%s_%d.jpg", base, size);
    }

}