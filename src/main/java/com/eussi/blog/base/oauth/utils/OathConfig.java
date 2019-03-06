package com.eussi.blog.base.oauth.utils;

import com.eussi.blog.base.oauth.APIConfig;

public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
