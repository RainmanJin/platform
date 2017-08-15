package com.icinfo.platform.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 可配置常量
 */
@Component
//@Lazy(false)
public class ConfigConstant {
    /**
     * 微信公众号：appId
     */
    public static String WECHAT_APP_ID;

    /**
     * 微信公众号：appSecret
     */
    public static String WECHAT_APP_SECRET;

    /**
     * 微信公众号：token
     */
    public static String WECHAT_TOKEN;

    @Value("${wechat.app.id}")
    public void setWechatAppId(String wechatAppId) {
        WECHAT_APP_ID = wechatAppId;
    }

    @Value("${wechat.app.secret}")
    public void setWechatAppSecret(String wechatAppSecret) {
        WECHAT_APP_SECRET = wechatAppSecret;
    }

    @Value("${wechat.token}")
    public void setWechatToken(String wechatToken) {
        WECHAT_TOKEN = wechatToken;
    }
}
