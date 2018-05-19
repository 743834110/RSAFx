package com.lingnan.sdk.algorithms.RSA;

/**
 * Created by Administrator on 2018/5/7.
 * 密钥对
 */
public class KeyPair {
    /**
     * 私钥
     */
    private Key privateKey;
    /**
     * 公钥
     */
    private Key publicKey;
    KeyPair(Key publicKey, Key privateKey){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }
}
