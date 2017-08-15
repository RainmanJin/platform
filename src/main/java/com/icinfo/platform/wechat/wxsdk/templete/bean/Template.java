package com.icinfo.platform.wechat.wxsdk.templete.bean;

import java.util.Map;

/**
 * TODO
 */
public class Template {
    private String touser;
    private String template_id;
    private String url;
    private Map<String, TemplateDate> data;

    public Template() {
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, TemplateDate> getData() {
        return data;
    }

    public void setData(Map<String, TemplateDate> data) {
        this.data = data;
    }
}
