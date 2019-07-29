package com.brook.utils;


import com.brook.config.KeyConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtils {

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */

    public static boolean verifyEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 生成验证码
     * @return
     */
    public static String makeVerifyCode(){
        int length = KeyConfig.VERIFY_CODE_LENGTH;
        return Random.getRandom(length, Random.TYPE.LETTER_CAPITAL_NUMBER);
    }

}
