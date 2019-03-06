package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.UserOauth;

public interface UserOauthMapper extends BaseMapper<UserOauth> {

    UserOauth findByAccessToken(String oauth_token);

    UserOauth findByUserId(long userId);

    UserOauth findByOauthUserId(String oauthUserId);
}