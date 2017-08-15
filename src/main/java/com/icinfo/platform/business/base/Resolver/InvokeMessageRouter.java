package com.icinfo.platform.business.base.Resolver;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
@Component
public class InvokeMessageRouter extends MessageRouter {
    Map<String, Class> classMap = new HashMap<String, Class>(){
        {
            put("add", AddResolver.class);
            put("sub", SubResolver.class);
        }
    };

    @Override
    protected Class getResolver(String clazzKey) throws Exception {
        return classMap.get(clazzKey);
    }
}
