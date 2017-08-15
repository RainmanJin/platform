package com.icinfo.platform.wechat.wxsdk.message.resolver;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface IResolver {
    /**
     * 消息处理
     *
     * @param message 需要处理的原始消息
     * @return 解析结果
     * @throws Exception
     */
    String resolve(Map<String, Object> message) throws Exception;
}
