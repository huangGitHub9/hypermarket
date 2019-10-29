package cn.xu.core.utils;

import java.util.UUID;

public class UUIDUtils {


    /*
    随机生成32为不重复的数字
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
