package com.icinfo.platform.wechat.wxsdk.menu.bean;

/**
 * 组合菜单
 */
public class ComplexButton extends Button {
    // 二级菜单数组
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
