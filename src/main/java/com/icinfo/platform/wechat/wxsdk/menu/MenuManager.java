package com.icinfo.platform.wechat.wxsdk.menu;

import com.icinfo.platform.wechat.wxsdk.base.BaseResult;
import com.icinfo.platform.wechat.wxsdk.menu.bean.Menu;
import com.icinfo.platform.wechat.wxsdk.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单 manager
 */
public class MenuManager {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 初始化菜单
     *
     * @param appId     用户唯一凭证
     * @param appSecret 用户唯一凭证密钥
     * @param menu      菜单
     * @throws Exception
     */
    public static void initMenu(String appId, String appSecret, Menu menu) throws Exception {
        BaseResult result = MenuAPI.createMenu(TokenManager.getToken(appId, appSecret).getAccess_token(), menu);
        if (result.isSuccess()) {
            logger.info("菜单创建成功！");
        } else {
            logger.error("菜单创建失败：{}", result.getErrcode() + ":" + result.getErrmsg());
        }
    }
}
