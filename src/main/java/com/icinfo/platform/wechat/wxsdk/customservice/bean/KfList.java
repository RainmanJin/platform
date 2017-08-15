package com.icinfo.platform.wechat.wxsdk.customservice.bean;

import com.icinfo.platform.wechat.wxsdk.base.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
public class KfList extends BaseResult{
    //客服列表
    private List<CustomerService> kf_list;

    public List<CustomerService> getKf_list() {
        return kf_list;
    }

    public void setKf_list(List<CustomerService> kf_list) {
        this.kf_list = kf_list;
    }
}
