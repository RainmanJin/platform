package com.icinfo.platform.wechat.wxsdk.customservice;

import com.icinfo.platform.common.constant.ConfigConstant;
import com.icinfo.platform.wechat.wxsdk.customservice.bean.KfList;
import com.icinfo.platform.wechat.wxsdk.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/1.
 */
public class CustomerServiceManager {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceManager.class);

    public static KfList getKfList() throws Exception {
        return CustomerServiceAPI.getKfList(TokenManager.getToken(ConfigConstant.WECHAT_APP_ID, ConfigConstant.WECHAT_APP_SECRET).getAccess_token());
    }
}
