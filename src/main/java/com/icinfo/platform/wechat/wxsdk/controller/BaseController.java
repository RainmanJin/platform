package com.icinfo.platform.wechat.wxsdk.controller;

import com.icinfo.platform.common.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 微信请求基本控制器
 */
public class BaseController {
    /**
     * 判断请求是否来源于微信
     *
     * @param request 请求
     * @param token   token
     * @return 判断结果：true表示请求来自微信
     * @throws Exception
     */
    protected boolean isWechatCall(HttpServletRequest request, String token) throws Exception {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            return false;
        }
        // 组装Hash参数
        String[] tmpArr = {token, timestamp, nonce};
        Arrays.sort(tmpArr);

        // 进行sha-1加密
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tmpArr.length; i++) {
            sb.append(tmpArr[i]);
        }
        return signature.equalsIgnoreCase(DigestUtils.sha1Hex(sb.toString()));
    }
}
