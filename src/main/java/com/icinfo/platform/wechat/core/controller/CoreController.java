package com.icinfo.platform.wechat.core.controller;

import com.icinfo.platform.common.constant.ConfigConstant;
import com.icinfo.platform.wechat.core.service.ICoreService;
import com.icinfo.platform.wechat.wxsdk.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信核心控制类
 */
@RestController
@RequestMapping("/core")
public class CoreController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    private ICoreService coreService;

    /**
     * 开发者接入
     *
     * @param request 请求
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/service", method = RequestMethod.GET, produces = "application/xml;charset=UTF-8")
    public String validate(HttpServletRequest request) throws Exception {
        if (isWechatCall(request, ConfigConstant.WECHAT_TOKEN)) {
            // 随机字符串
            String echostr = request.getParameter("echostr");
            logger.info("微信接入成功！");
            return echostr;
        }
        logger.error("微信接入失败！");
        return "error";
    }

    /**
     * 微信消息处理
     *
     * @param request 请求
     * @return 处理结果
     * @throws Exception
     */
    @RequestMapping(value = "/service", method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
    public String processMsg(HttpServletRequest request) throws Exception {
        if (isWechatCall(request, ConfigConstant.WECHAT_TOKEN)) {
            return "";
        }
        String message = coreService.processRequest(request);
        System.out.println(message);
        return message;
    }
}
