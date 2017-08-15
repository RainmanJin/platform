package com.icinfo.platform.wechat.wxsdk.menu;

import com.icinfo.platform.common.util.JSONUtils;
import com.icinfo.platform.wechat.wxsdk.base.BaseResult;
import com.icinfo.platform.wechat.wxsdk.base.BaseAPI;
import com.icinfo.platform.wechat.wxsdk.common.client.HttpClientExecutor;
import com.icinfo.platform.wechat.wxsdk.menu.bean.Menu;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;

/**
 * 菜单 API
 */
public class MenuAPI extends BaseAPI {
    /**
     * 菜单创建
     *
     * @param access_token 接口调用凭证
     * @param menu         菜单
     * @return 创建结果
     * @throws Exception
     */
    public static BaseResult createMenu(String access_token, Menu menu) throws Exception {
        HttpUriRequest request = RequestBuilder.post()
                .setHeader(jsonHeader)
                .setUri(BASE_URI + "/cgi-bin/menu/create")
                .addParameter(PARAM_ACCESS_TOKEN, access_token)
                .setEntity(new StringEntity(JSONUtils.parse(menu), Charset.forName("utf-8")))
                .build();
        return HttpClientExecutor.executeJsonResult(request, BaseResult.class);
    }

    /**
     * 获取菜单
     *
     * @param access_token 接口调用凭证
     * @return 菜单数据
     * @throws Exception
     */
    public static String getMenu(String access_token) throws Exception {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(BASE_URI + "/cgi-bin/menu/get")
                .addParameter(PARAM_ACCESS_TOKEN, access_token)
                .build();
        return HttpClientExecutor.executeJsonResult(request, String.class);
    }

    /**
     * 删除全部菜单
     *
     * @param access_token 接口调用凭证
     * @return 删除结果
     * @throws Exception
     */
    public static BaseResult deleteMenu(String access_token) throws Exception {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(BASE_URI + "/cgi-bin/menu/delete")
                .addParameter(PARAM_ACCESS_TOKEN, access_token)
                .build();
        return HttpClientExecutor.executeJsonResult(request, BaseResult.class);
    }
}
