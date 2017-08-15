package com.icinfo.platform.business.service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface IInvokeService {
    Object doProcess(Map<String, Object> map) throws Exception;
}
