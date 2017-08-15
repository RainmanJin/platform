package com.icinfo.platform.wechat.wxsdk.message.resolver;

import com.icinfo.platform.wechat.wxsdk.base.BaseMessage;
import com.icinfo.platform.wechat.wxsdk.common.utils.XMLUtils;
import com.icinfo.platform.wechat.wxsdk.message.bean.TextMessage;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/27.
 */
public abstract class BaseResolver {
    public static final String MESSAGE_TEXT = "text";
    /**
     * 设置基础消息
     *
     * @param baseMessage 基础消息
     * @param message     用户消息内容
     * @throws Exception
     */
    protected void setBaseMessage(BaseMessage baseMessage, Map<String, Object> message) throws Exception {
        baseMessage.setFromUserName(message.get("ToUserName").toString());
        baseMessage.setToUserName(message.get("FromUserName").toString());
        baseMessage.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 生成文本消息
     *
     * @param message
     * @param content
     * @return
     * @throws Exception
     */
    protected String createTextMessage(Map<String, Object> message, String content) throws Exception{
        TextMessage textMessage = new TextMessage();
        setBaseMessage(textMessage, message);
        textMessage.setMsgType(MESSAGE_TEXT);
        textMessage.setContent(content);
        return XMLUtils.parseXml(textMessage);
    }
}
