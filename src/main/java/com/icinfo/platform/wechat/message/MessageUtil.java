package com.icinfo.platform.wechat.message;

/**
 * 消息处理工具类
 */
public class MessageUtil {
    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：小视频
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "CLICK";

    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";




    //
    ///**
    // * 图片消息对象转换成xml
    // *
    // * @param imageMessage 图片消息对象
    // * @return xml
    // */
    //public static String messageToXml(ImageMessage imageMessage) {
    //    xstream.alias("xml", imageMessage.getClass());
    //    return xstream.toXML(imageMessage);
    //}
    //
    ///**
    // * 语音消息对象转换成xml
    // *
    // * @param voiceMessage 语音消息对象
    // * @return xml
    // */
    //public static String messageToXml(VoiceMessage voiceMessage) {
    //    xstream.alias("xml", voiceMessage.getClass());
    //    return xstream.toXML(voiceMessage);
    //}
    //
    ///**
    // * 视频消息对象转换成xml
    // *
    // * @param videoMessage 视频消息对象
    // * @return xml
    // */
    //public static String messageToXml(VideoMessage videoMessage) {
    //    xstream.alias("xml", videoMessage.getClass());
    //    return xstream.toXML(videoMessage);
    //}
    //
    ///**
    // * 音乐消息对象转换成xml
    // *
    // * @param musicMessage 音乐消息对象
    // * @return xml
    // */
    //public static String messageToXml(MusicMessage musicMessage) {
    //    xstream.alias("xml", musicMessage.getClass());
    //    return xstream.toXML(musicMessage);
    //}
    //
    ///**
    // * 图文消息对象转换成xml
    // *
    // * @param newsMessage 图文消息对象
    // * @return xml
    // */
    //public static String messageToXml(NewsMessage newsMessage) {
    //    xstream.alias("xml", newsMessage.getClass());
    //    xstream.alias("item", new Article().getClass());
    //    return xstream.toXML(newsMessage);
    //}
}
