package com.icinfo.platform.wechat.wxsdk.message.bean;

import com.icinfo.platform.wechat.wxsdk.base.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 响应文本消息
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
