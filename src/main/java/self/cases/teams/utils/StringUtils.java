package self.cases.teams.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 */
public class StringUtils {
	
	 /**
     * 检查指定的字符串是否为 NUll
     * 返回true，指定的字符串是 NULL
     * 返回false，指定的字符串不是 NULL
     * @param str 要检查的字符串
     * @return
     */
    public static Boolean isNull(String str){

        if(str == null){

            return true;
        }

        return false;
    }

    /**
     * 检查指定的字符串是否不为 NUll
     * 返回true，指定的字符串不为 NULL
     * 返回false，指定的字符串是 NULL
     * @param str 要检查的字符串
     * @return
     */
    public static Boolean isNotNull(String str){

        Boolean flag = !isNull(str);

        return flag;
    }

    /**
     * 检查指定的字符串是否为 NULL 或者空字符串
     * 返回true，指定的字符串是 NULL 或者空字符串
     * 返回 false，指定的字符串不是 NULL 或者空字符粗
     * @param str 要检查的字符串
     * @return
     */
    public static Boolean isNullOrEmpty(String str){

        if(str == null){

            return true;
        }

        if(str.length() <= 0){

            return true;
        }

        return false;
    }

    /**
     * 检查指定的字符串是否不为 NULL 或者空字符串
     * 返回true，指定的字符串不是 NULL 或者空字符串
     * 返回 false，指定的字符串是 NULL 或者空字符粗
     * @param str
     * @return
     */
    public static Boolean isNotNullOrEmpty(String str){

        Boolean flag = !isNullOrEmpty(str);

        return flag;
    }
    
    /**
	 * 检查指定的字符串中
	 * @param str 要检查的字符串
	 * @param flag 指定的字符串
	 * @return
	 */
	public static boolean isExit(String str, String flag) {
		
		if(str.indexOf(flag) > 0) {
			
			return false;
		}
		
		return true;
	}
    
    /**
	 * 获取字符串的长度
	 * 如果字符串是 null，返回 0
	 * @param str
	 * @return
	 */
	public static int length(String str) {
		
		return str == null ? 0 : str.length();
	}
}
