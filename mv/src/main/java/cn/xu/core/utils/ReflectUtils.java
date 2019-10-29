package cn.xu.core.utils;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static String getAttrValueByMethodName(Object obj,String methodName){
        Class clazz = obj.getClass();
        Class[] parameterTypes = {};
        String content =null;
        try {
            Method method = clazz.getMethod(methodName,parameterTypes);
            content = (String)method.invoke(obj,null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }
}
