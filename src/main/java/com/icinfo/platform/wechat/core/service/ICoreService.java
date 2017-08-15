package com.icinfo.platform.wechat.core.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信service接口
 */
public interface ICoreService {
    /**
     * 处理微信请求
     *
     * @param request 请求
     * @return 处理结果
     * @throws Exception
     */
    String processRequest(HttpServletRequest request) throws Exception;
}
