package com.icinfo.platform.wechat.wxsdk.menu.bean;

/**
 * 按钮
 */
public class Button {
    //菜单标题:最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
