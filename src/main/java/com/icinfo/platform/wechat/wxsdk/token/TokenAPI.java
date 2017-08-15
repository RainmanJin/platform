package com.icinfo.platform.wechat.wxsdk.token;

import com.icinfo.platform.wechat.wxsdk.base.BaseAPI;
import com.icinfo.platform.wechat.wxsdk.common.client.HttpClientExecutor;
import com.icinfo.platform.wechat.wxsdk.token.bean.Token;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 * access_token API
 */
public class TokenAPI extends BaseAPI {
    /**
     * 接口调用获取access_token
     *
     * @param appId     用户唯一凭证
     * @param appSecret 用户唯一凭证密钥
     * @return token
     * @throws Exception
     */
    public static Token getToken(String appId, String appSecret) throws Exception {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(BASE_URI + "/cgi-bin/token")
                .addParameter("grant_type", "client_credential")
                .addParameter("appid", appId)
                .addParameter("secret", appSecret)
                .build();
        return HttpClientExecutor.executeJsonResult(request, Token.class);
    }
}
