package com.icinfo.platform.wechat.message;

import com.icinfo.platform.wechat.message.resolver.WechatSubscribeEventResolver;
import com.icinfo.platform.wechat.wxsdk.message.MessageRouter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息路由器
 */
@Component
public class WechatMessageRouter extends MessageRouter {
    /**
     * 消息转换器
     */
    private static Map<String, Class> routerMap = new HashMap<String, Class>() {
        {
            put("eventsubscribe", WechatSubscribeEventResolver.class);
        }
    };

    /**
     * 获取消息转换器
     *
     * @param claszzKey 转换器标识
     * @return 消息转换器
     */
    @Override
    protected Class getResolver(String claszzKey) {
        return routerMap.get(claszzKey);
    }
}
