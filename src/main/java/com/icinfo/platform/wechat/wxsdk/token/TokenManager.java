package com.icinfo.platform.wechat.wxsdk.token;

import com.icinfo.platform.wechat.wxsdk.token.bean.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * access_token 中控服务器
 */
public class TokenManager {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    /**
     * token
     */
    public static Token token;

    /**
     * 获取token
     *
     * @param appId     用户唯一凭证
     * @param appSecret 用户唯一凭证密钥
     * @return token
     * @throws Exception
     */
    public static Token getToken(String appId, String appSecret) throws Exception {
        // 1.如果token为空或者token失效，刷新token
        if (token == null || token.getExpires_in() < System.currentTimeMillis()) {
            refreshToken(appId, appSecret);
        }
        return token;
    }

    /**
     * 刷新token
     *
     * @param appId     用户唯一凭证
     * @param appSecret 用户唯一凭证密钥
     * @throws Exception
     */
    public static void refreshToken(String appId, String appSecret) throws Exception {
        token = TokenAPI.getToken(appId, appSecret);
        // 1.获取token失败
        if (!token.isSuccess()) {
            logger.error("获取access_token失败:{}", token.getErrcode() + ":" + token.getErrmsg());
            return;
        }
        // 2.设置过期时间，提前100秒过期
        token.setExpires_in(System.currentTimeMillis() + 7100 * 100);
    }
}
