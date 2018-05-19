package com.lingnan.sdk.algorithms.SHA;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Administrator on 2018/5/2.
 */
public class SHADigest {
    private String key = "rrdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";
    @Test
    public void test() throws NoSuchAlgorithmException {
        SHA_1 sha = new SHA_1();
        String hash = sha.digest(key.getBytes());
        System.out.println(hash);
        System.out.println(Integer.MAX_VALUE * 8);
    }
}
