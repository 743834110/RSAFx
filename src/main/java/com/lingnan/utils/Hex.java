package com.lingnan.utils;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/7.
 */
public class Hex {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();


    /**
     * 将byte数组数据转换为16进制长度
     * 数位对应关系：16进制数组长度 = 字节数组长度 << 1
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        char[] hexDigit = new char[data.length << 1];
        for (int i = 0, j = 0; i < data.length; i++) {
            hexDigit[j++] = HEX_DIGITS[(data[i] & 0XF0) >> 4];
            hexDigit[j++] = HEX_DIGITS[data[i] & 0x0F];
        }
        return new String(hexDigit);
    }

    /**
     * 十六进制字符串转换为byte数组
     * @param hex
     * @return
     */
    public static byte[] decode(String hex){
        int length = hex.length() * 4;
        byte[] bytes = new byte[(int) Math.ceil(1.0 * length / 8)];
        ResourceBundle radixBundle = ResourceBundle.getBundle("base");
        length = hex.length();
        char[] chars = hex.toCharArray();
        Byte temp ;
        for (int i = 0; i < length; i += 2) {
            temp = Byte.valueOf(radixBundle.getString("" + chars[i]));
            if (i + 1 < length)
                temp = (byte) (temp << 4 |  Byte.valueOf(radixBundle.getString("" + chars[i + 1])) );
           bytes[i / 2] = temp;
        }
        return bytes;
    }


}
