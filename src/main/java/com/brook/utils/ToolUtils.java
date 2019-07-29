package com.brook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author: xuequan
 * @Date: 2019/6/27 21:10
 * @Description:
 */
public class ToolUtils {

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static boolean validString(String str){
        // 允许字母、数字、下划线、横线和空格
        String regEx="^[0-9a-zA-Z _-]+$"  ;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
