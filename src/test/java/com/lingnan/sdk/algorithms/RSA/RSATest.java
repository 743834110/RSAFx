package com.lingnan.sdk.algorithms.RSA;

import com.lingnan.utils.Hex;
import org.joou.UByte;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.util.Base64;

/**
 * Created by Administrator on 2018/5/7.
 */
public class RSATest {

    @Test
    public void test() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        RSA rsa = RSA.getInstance();
        KeyPair keyPair = rsa.generateKeyPair(128);
        byte[] bytes = rsa.decrypt("hellrrrrrrrrrfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffrrrrrrrrrrrrrro world".getBytes(), keyPair.getPublicKey());
        System.out.println(bytes.length * 8);

    }
}
