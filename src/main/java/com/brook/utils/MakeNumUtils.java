package com.brook.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class MakeNumUtils {

    /**
     * 生成Number值
     *
     * @param length
     * @param prefix
     * @return
     */
    public static String makeNum(int length, String prefix) {
        String key;
        if (StringUtils.isNotEmpty(prefix)) {
            key = prefix + "-" + Random.getRandom(length, Random.TYPE.LETTER_CAPITAL_NUMBER);
        } else {
            key = Random.getRandom(length, Random.TYPE.LETTER_CAPITAL_NUMBER);
        }
        return key;
    }

    public static String makeCode(int length) {
        String code = Random.getRandom(length, Random.TYPE.NUMBER);
        if (code.startsWith("0")) {
            code = code.replaceFirst("0", "1");
        }
        return code;
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 生成Number值
     *
     * @param length
     * @return
     */
    public static String makeNum(int length) {
        return makeNum(length, null);
    }
}
