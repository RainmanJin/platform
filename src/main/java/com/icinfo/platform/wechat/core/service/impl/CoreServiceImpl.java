package com.icinfo.platform.wechat.core.service.impl;

import com.icinfo.platform.wechat.core.service.ICoreService;
import com.icinfo.platform.wechat.message.WechatMessageRouter;
import com.icinfo.platform.wechat.wxsdk.common.utils.XMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信service实现类
 */
@Service
public class CoreServiceImpl implements ICoreService {
    /**
     * 消息路由器注入
     */
    @Autowired
    private WechatMessageRouter messageRouter;

    /**
     * 处理微信请求
     *
     * @param request 请求
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public String processRequest(HttpServletRequest request) throws Exception {
        // 1.解析xml数据
        Map<String, String> message = XMLUtils.parseXml(request);

        // 2.消息处理
        return messageRouter.route(message);
    }
}
