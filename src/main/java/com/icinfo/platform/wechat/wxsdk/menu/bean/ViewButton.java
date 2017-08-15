package com.icinfo.platform.wechat.wxsdk.menu.bean;

/**
 * view类型按钮
 */
public class ViewButton extends Button {
    //按钮类型：view
    private String type = "view";

    //网页链接
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
