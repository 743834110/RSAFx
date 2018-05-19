package com.lingnan.utils;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/5/14.
 */
public class IOUtils {
    private IOUtils(){}

    /**
     * 获取文件字符集
     * @return
     */
    private static String getCharset(File file) throws IOException {
        byte[] b = new byte[3];
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(b);
        inputStream.close();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65)
            return "UTF-8";
        return "GBK";
    }

    /**
     * 从文件中读取内容
     * @param file
     * @return
     * @throws IOException
     */
    public static String read(File file ) throws IOException {
        String charset = getCharset(file);
        Scanner scanner = new Scanner(file, charset);
        String text = scanner.useDelimiter("\\a").hasNext()? scanner.next(): "";
        scanner.close();
        return text;
    }

    /**
     * 写出文件内容
     * @param file
     * @param text
     * @throws IOException
     */
    public static void write(File file, String text) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(text);
        bufferedWriter.close();
    }
}
