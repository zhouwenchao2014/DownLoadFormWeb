package cn.myhomespace.zhou.utils;

/**
 * Created by zhouwenchao on 16/2/24.
 */
public class SystemMessage {
    public static String getOs(){
        return System.getProperty("os.name");
    }

    public static  String getUserHome(){
        return System.getProperty("user.home");
    }
}
