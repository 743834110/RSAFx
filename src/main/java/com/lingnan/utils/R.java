package com.lingnan.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Administrator on 2018/5/11.
 */
public class R {
    private static String rootPath = new File("").getAbsolutePath();
    private R(){}

    public static URL getResourceAsURL(String pathName){
        return R.class.getClassLoader().getResource(pathName);
    }
}
