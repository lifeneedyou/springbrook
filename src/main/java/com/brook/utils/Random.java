package com.brook.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Random {
    private static String[] lowercase = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] capital = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] number = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private static String[] sign = new String[]{"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "`", "-", "=", "{", "}", "|", ":", "\"", "<", ">", "?", "[", "]", "\\", ";", "'", ",", ".", "/"};
    private static java.util.Random random = new java.util.Random();

    public Random() {
    }

    public static String getRandom(int num, Random.TYPE type) {
        ArrayList<String> temp = new ArrayList();
        StringBuffer sb = new StringBuffer();
        if (type == Random.TYPE.LETTER) {
            temp.addAll(Arrays.asList(lowercase));
        } else if (type == Random.TYPE.CAPITAL) {
            temp.addAll(Arrays.asList(capital));
        } else if (type == Random.TYPE.NUMBER) {
            temp.addAll(Arrays.asList(number));
        } else if (type == Random.TYPE.SIGN) {
            temp.addAll(Arrays.asList(sign));
        } else if (type == Random.TYPE.LETTER_CAPITAL) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
        } else if (type == Random.TYPE.LETTER_NUMBER) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(number));
        } else if (type == Random.TYPE.LETTER_CAPITAL_NUMBER) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
            temp.addAll(Arrays.asList(number));
        } else if (type == Random.TYPE.LETTER_CAPITAL_NUMBER_SIGN) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
            temp.addAll(Arrays.asList(number));
            temp.addAll(Arrays.asList(sign));
        }

        for(int i = 0; i < num; ++i) {
            sb.append((String)temp.get(random.nextInt(temp.size())));
        }

        return sb.toString();
    }

    public static enum TYPE {
        LETTER,
        CAPITAL,
        NUMBER,
        SIGN,
        LETTER_CAPITAL,
        LETTER_NUMBER,
        LETTER_CAPITAL_NUMBER,
        LETTER_CAPITAL_NUMBER_SIGN;

        private TYPE() {
        }
    }
}