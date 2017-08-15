package com.icinfo.platform.wechat.wxsdk.menu.bean;

/**
 * click类型按钮
 */
public class ClickButton extends Button {
    //按钮类型：click
    private String type = "click";

    //菜单KEY值
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
