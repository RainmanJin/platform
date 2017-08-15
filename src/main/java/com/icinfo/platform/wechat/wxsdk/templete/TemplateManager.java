package com.icinfo.platform.wechat.wxsdk.templete;

import com.icinfo.platform.wechat.wxsdk.templete.bean.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 */
public class TemplateManager {
    private static Logger logger = LoggerFactory.getLogger(TemplateManager.class);

    public static void sendTemplete(Template template, String appID, String appsecret) throws Exception {
        //BaseResult result = TemplateAPI.send(TokenManager.getToken(appID, appsecret).getAccess_token(), template);
        //if (result.isSuccess()) {
        //    logger.info("成功！");
        //} else {
        //    logger.error("失败:{}", result.getErrcode() + "<->" + result.getErrmsg());
        //}
    }
}
