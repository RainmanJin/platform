package com.icinfo.platform.wechat.wxsdk.message;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 消息路由
 */
public abstract class MessageRouter {
    /**
     * 获取消息转换器
     *
     * @param claszzKey 转换器标识
     * @return 转换器类名
     */
    protected abstract Class getResolver(String claszzKey);

    /**
     * 消息路由
     *
     * @param message 消息
     * @return 路由结果
     * @throws Exception
     */
    public String route(Map<String, String> message) throws Exception {
        // 获取msgType 和 event 组合作为路由标识
        String msgType = message.get("MsgType");
        String event = message.get("Event");

        Class clazz = getResolver((msgType + event).replace("null", "").toLowerCase());

        // 如果对应解析器还未实现，则返回空文本，微信服务器不会对此作任何处理，并且不会发起重试
        if (clazz == null) {
            return "";
        }

        Method method = clazz.getDeclaredMethod("resolve", new Class[]{Map.class});
        if (method == null) {
            return "";
        }
        return (String) method.invoke(clazz.newInstance(), message);
    }
}
