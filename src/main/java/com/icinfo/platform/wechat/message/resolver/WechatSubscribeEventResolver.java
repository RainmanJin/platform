package com.icinfo.platform.wechat.message.resolver;

import com.icinfo.platform.wechat.wxsdk.message.resolver.SubscribeEventResolver;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/27.
 */
public class WechatSubscribeEventResolver extends SubscribeEventResolver {

    /**
     * 关注事件处理
     *
     * @param message 需要处理的原始消息
     * @return 关注事件处理结果
     * @throws Exception
     */
    @Override
    public String resolve(Map<String, Object> message) throws Exception {
        return createTextMessage(message, "欢迎关注！");
    }
}
