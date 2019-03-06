package com.eussi.blog.base.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eussi.blog.base.oauth.utils.EnumOauthTypeBean;
import com.eussi.blog.base.oauth.utils.OathConfig;
import com.eussi.blog.base.oauth.utils.OpenOauthBean;
import com.eussi.blog.base.oauth.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OauthSina extends Oauth {
    //参考网址 https://open.weibo.com/wiki/%E6%8E%88%E6%9D%83%E6%9C%BA%E5%88%B6%E8%AF%B4%E6%98%8E
    private static final Logger LOGGER = LoggerFactory.getLogger(OauthSina.class);
    //请求用户授权Token
    private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
    //获取授权过的Access Token
    private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    //授权信息查询接口
    private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
    //根据用户ID获取用户信息
    private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";

    public static OauthSina me() {
        return new OauthSina();
    }

    public OauthSina() {
        setClientId(OathConfig.getValue("openid_sina"));
        setClientSecret(OathConfig.getValue("openkey_sina"));
        setRedirectUri(OathConfig.getValue("redirect_sina"));
    }

    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        //构造授权页请求字符串
        Map<String, String> params = new HashMap<>();
//        params.put("response_type", "code");//微博接口说明中未指定的参数
        params.put("client_id", getClientId());//申请应用时分配的AppKey。
        params.put("redirect_uri", getRedirectUri());//授权回调地址，站外应用需与设置的回调地址一致，站内应用需填写canvas page的地址
        if (StringUtils.isNotBlank(state)) {
            params.put("state", state);//用于保持请求和回调的状态
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", getClientId());//申请应用时分配的AppKey
        params.put("client_secret", getClientSecret());//申请应用时分配的AppSecret
        params.put("grant_type", "authorization_code");//请求的类型，填写authorization_code

        //grant_type为authorization_code时, 填写以下内容
        params.put("code", code);//
        params.put("redirect_uri", getRedirectUri());

        //post请求，并获取返回json字符串中的access_token
        //用户授权的唯一票据，用于调用微博的开放接口，同时也是第三方应用验证微博用户登录的唯一票据
        // 第三方应用应该用该票据和自己应用内的用户建立唯一影射关系，来识别登录状态，
        // 不能使用本返回值里的UID字段来做登录识别。
        String token = TokenUtil.getAccessToken(super.doPost(TOKEN_URL, params));

        LOGGER.debug(token);
        return token;
    }

    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);

        //获取返回结果中的uid 即授权用户的uid
        String openid = TokenUtil.getUid(super.doPost(TOKEN_INFO_URL, params));
        LOGGER.debug(openid);
        return openid;
    }

    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        //传入access_token和uid返回用户各项信息
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("access_token", accessToken);

        String userInfo = super.doGet(USER_INFO_URL, params);
        LOGGER.debug(userInfo);
        return userInfo;
    }

    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        //根据返回结果中的Code，调用oauth2/access_token接口，获取授权后的access token
        String accessToken = getTokenByCode(code);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }

        //通过accessToken调用 oauth2/get_token_info接口
        String uid = getTokenInfo(accessToken);
        if (StringUtils.isBlank(uid)) {
            return null;
        }

        //通过accessToken和uid调用users/show.json接口获取用户信息
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, uid));
        dataMap.put("access_token", accessToken);
        LOGGER.debug(JSON.toJSONString(dataMap));
        return dataMap;
    }


    public OpenOauthBean getUserBeanByCode(String code)
            throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        OpenOauthBean openOauthBean = null;
        //根据返回结果中的Code，调用oauth2/access_token接口等几个接口，最终获取用户信息
        JSONObject userInfo = me().getUserInfoByCode(code);
        /*
            返回值字段	        字段类型	       字段说明
            id	                int64	        用户UID
            idstr	            string	        字符串型的用户UID
            screen_name	        string	        用户昵称
            name	            string	        友好显示名称
            province	        int	            用户所在省级ID
            city	            int	            用户所在城市ID
            location	        string	        用户所在地
            description	        string	        用户个人描述
            url	                string	        用户博客地址
            profile_image_url	string	        用户头像地址（中图），50×50像素
            profile_url	        string	        用户的微博统一URL地址
            domain	            string	        用户的个性化域名
            weihao	            string	        用户的微号
            gender	            string	        性别，m：男、f：女、n：未知
            followers_count	    int	            粉丝数
            friends_count	    int	            关注数
            statuses_count	    int	            微博数
            favourites_count	int	            收藏数
            created_at	        string	        用户创建（注册）时间
            following	        boolean	        暂未支持
            allow_all_act_msg	boolean	        是否允许所有人给我发私信，true：是，false：否
            geo_enabled	        boolean	        是否允许标识用户的地理位置，true：是，false：否
            verified	        boolean	        是否是微博认证用户，即加V用户，true：是，false：否
            verified_type	    int	            暂未支持
            remark	            string	        用户备注信息，只有在查询用户关系时才返回此字段
            status	            object	        用户的最近一条微博信息字段 详细
            allow_all_comment	boolean	        是否允许所有人对我的微博进行评论，true：是，false：否
            avatar_large	    string	        用户头像地址（大图），180×180像素
            avatar_hd	        string	        用户头像地址（高清），高清头像原图
            verified_reason	    string	        认证原因
            follow_me	        boolean	        该用户是否关注当前登录用户，true：是，false：否
            online_status	    int	            用户的在线状态，0：不在线、1：在线
            bi_followers_count	int	            用户的互粉数
            lang	            string	        用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
         */
        openOauthBean = new OpenOauthBean();
        String openid = userInfo.getString("id");
        String accessToken = userInfo.getString("access_token");
        String nickname = userInfo.getString("screen_name");
        String photoUrl = userInfo.getString("profile_image_url");

        openOauthBean.setOauthCode(code);
        openOauthBean.setAccessToken(accessToken);
        openOauthBean.setExpireIn("");
        openOauthBean.setOauthUserId(openid);
        openOauthBean.setOauthType(EnumOauthTypeBean.TYPE_SINA.getValue());
        openOauthBean.setUsername("SN" + Arrays.hashCode(openid.getBytes()));
        openOauthBean.setNickname(nickname);
        openOauthBean.setAvatar(photoUrl);

        return openOauthBean;
    }
}
