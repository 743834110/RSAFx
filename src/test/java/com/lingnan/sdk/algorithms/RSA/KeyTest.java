package com.lingnan.sdk.algorithms.RSA;

import org.junit.Test;

/**
 * Created by Administrator on 2018/5/18.
 */
public class KeyTest {
    @Test
    public void testKey(){
        RSA rsa = RSA.getInstance();
        KeyPair keyPair = rsa.generateKeyPair(128);
        byte[] bytes = rsa.encrypt("恶风".getBytes(), keyPair.getPrivateKey());
        String string  = keyPair.getPublicKey().toString();
        Key publicKey = Key.valueOf(string);
        System.out.println(new String(rsa.decrypt(bytes, publicKey)));
    }
}
