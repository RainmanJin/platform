package com.icinfo.platform.business.base.Resolver;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
public abstract class MessageRouter {
    protected abstract Class getResolver(String clazzKey) throws Exception;

    public String router(Map<String, Object> map)throws Exception{
        String type = String.valueOf(map.get("type")).toLowerCase();
        Class resolver = getResolver(type);

        if(resolver==null){
            return "";
        }

        Method method = resolver.getDeclaredMethod("resolver", new Class[]{Map.class});

        if(method == null){
            return  "";
        }
        return (String) method.invoke(resolver.newInstance(), map);
    }
}

