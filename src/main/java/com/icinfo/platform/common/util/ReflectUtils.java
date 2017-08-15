package com.icinfo.platform.common.util;

/**
 * Created by Administrator on 2017/6/9.
 */
public class ReflectUtils {
    /**
     * 通过一个对象获得完整的包名和类名
     *
     * @param o 对象
     * @return 完整的包名和类名
     * @throws Exception
     */
    public static String getClassName(Object o) throws Exception{
        return o.getClass().getName();
    }


}
