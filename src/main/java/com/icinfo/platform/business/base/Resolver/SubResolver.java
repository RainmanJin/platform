package com.icinfo.platform.business.base.Resolver;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
public class SubResolver implements IResolver {
    @Override
    public String resolver(Map<String, Object> map) throws Exception {
        Object paramA = map.get("paramA");
        Object paramB = map.get("paramB");

        int sub = Integer.sum(paramA == null ? 0 : Integer.valueOf(paramA.toString()), paramB == null ? 0 : -Integer.valueOf(paramB.toString()));
        return String.valueOf(sub);
    }
}
