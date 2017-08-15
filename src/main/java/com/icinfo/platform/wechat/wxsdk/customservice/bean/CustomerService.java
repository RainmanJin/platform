package com.icinfo.platform.wechat.wxsdk.customservice.bean;

/**
 * Created by Administrator on 2017/6/1.
 */
public class CustomerService {
    //完整客服帐号，格式为：帐号前缀@公众号微信号
    private String kf_account;
    //客服头像
    private String kf_headimgurl;
    //客服编号
    private String kf_id;
    //客服昵称
    private String kf_nick;
    //如果客服帐号已绑定了客服人员微信号，则此处显示微信号
    private String kf_wx;

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getKf_headimgurl() {
        return kf_headimgurl;
    }

    public void setKf_headimgurl(String kf_headimgurl) {
        this.kf_headimgurl = kf_headimgurl;
    }

    public String getKf_id() {
        return kf_id;
    }

    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }

    public String getKf_nick() {
        return kf_nick;
    }

    public void setKf_nick(String kf_nick) {
        this.kf_nick = kf_nick;
    }

    public String getKf_wx() {
        return kf_wx;
    }

    public void setKf_wx(String kf_wx) {
        this.kf_wx = kf_wx;
    }
}
