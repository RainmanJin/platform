package com.icinfo.platform.wechat.wxsdk.base;

/**
 * 微信接口范围基础类
 */
public class BaseResult {
    /**
     * 微信接口返回正常状态码
     */
    private static final String SUCCESS_CODE = "0";

    /**
     * 错误码
     */
    private String errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 判断接口调用是否成功
     *
     * @return 成功:true,失败：false
     */
    public boolean isSuccess() {
        return errcode == null || errcode.isEmpty() || errcode.equals(SUCCESS_CODE);
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
