package com.icinfo.platform.business.base.Resolver;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface IResolver {
    String resolver(Map<String, Object> map) throws Exception;
}
