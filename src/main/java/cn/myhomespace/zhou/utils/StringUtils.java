package cn.myhomespace.zhou.utils;

/**
 * Created by zhouwenchao on 16/2/18.
 */
public class StringUtils {
    /**
     * 如果str不为空或者空字符串返回true
     * 反则返回false
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        if("".equals(str)||str==null){
            return false;
        }
        return true;
    }
}
