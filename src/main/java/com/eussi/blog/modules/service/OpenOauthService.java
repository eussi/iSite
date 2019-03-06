package com.eussi.blog.modules.service;


import com.eussi.blog.modules.vo.OpenOauthVO;
import com.eussi.blog.modules.vo.UserVO;

/**
 * @author wangxm on 2019/3/6.
 */
public interface OpenOauthService {
    //通过 oauth_token 查询 user
    UserVO getUserByOauthToken(String oauth_token);

    OpenOauthVO getOauthByToken(String oauth_token);
    
    OpenOauthVO getOauthByOauthUserId(String oauthUserId);

    OpenOauthVO getOauthByUid(long userId);

    boolean checkIsOriginalPassword(long userId);

    void saveOauthToken(OpenOauthVO oauth);

}
