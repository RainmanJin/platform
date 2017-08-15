package com.icinfo.platform.wechat.wxsdk.templete.bean;

/**
 * TODO
 */
public class TemplateDate {
    private Object value;
    private String color;

    public TemplateDate() {
    }

    public TemplateDate(Object value, String color) {
        this.value = value;
        this.color = color;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
