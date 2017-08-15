package com.icinfo.platform.wechat.wxsdk.customservice;

import com.icinfo.platform.wechat.wxsdk.base.BaseAPI;
import com.icinfo.platform.wechat.wxsdk.base.BaseResult;
import com.icinfo.platform.wechat.wxsdk.common.client.HttpClientExecutor;
import com.icinfo.platform.wechat.wxsdk.common.client.JSONUtils;
import com.icinfo.platform.wechat.wxsdk.customservice.bean.KfList;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 客服API
 */
public class CustomerServiceAPI extends BaseAPI {
    /**
     * 获取客服基本信息
     *
     * @param access_token 接口调用凭证
     * @return 客服基本信息
     * @throws Exception
     */
    public static KfList getKfList(String access_token) throws Exception {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(BASE_URI + "/cgi-bin/customservice/getkflist")
                .addParameter("access_token", access_token)
                .build();

        return HttpClientExecutor.executeJsonResult(request, KfList.class);
    }

    /**
     * 添加客服帐号
     *
     * @param access_token 接口调用凭证
     * @param map          客服数据
     *                     kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     *                     nickname   客服昵称，最长16个字
     * @return 新增结果
     * @throws Exception
     */
    public static BaseResult add(String access_token, Map<String, Object> map) throws Exception {
        HttpUriRequest request = RequestBuilder.post()
                .setHeader(jsonHeader)
                .setUri(BASE_URI + "/customservice/kfaccount/add")
                .addParameter("access_token", access_token)
                .setEntity(new StringEntity(JSONUtils.parse(map), Charset.forName("utf-8")))
                .build();
        return HttpClientExecutor.executeJsonResult(request, BaseResult.class);
    }
}
