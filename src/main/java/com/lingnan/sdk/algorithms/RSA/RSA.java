package com.lingnan.sdk.algorithms.RSA;

import com.lingnan.utils.R;
import javafx.util.converter.BigIntegerStringConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Administrator on 2018/5/2.
 *
 */
public class RSA {
    public enum Type {
        PUBLIC_KEY, PRIVATE_KEY
    }

    private static RSA rsa = new RSA();
    private RSA(){}
    public static RSA getInstance(){
        return rsa;
    }

    private SecureRandom secureRandom = new SecureRandom();
//    private Integer bitLength;
    private KeyPair keyPair;
    /**
     * 根据要求密钥位数产生的密钥对。
     * @param bitLength 密钥长度
     */
    public KeyPair generateKeyPair(int bitLength){
//        if (this.bitLength != null && this.bitLength == bitLength) {
//            return this.keyPair;
//        }
//        this.bitLength = bitLength;
        //素数p、q获取
        BigInteger p = BigInteger.probablePrime(bitLength, this.secureRandom);
        BigInteger q = BigInteger.probablePrime(bitLength, this.secureRandom);
        //n = q * p
        BigInteger n = p.multiply(q);
        // 欧拉函数 = (q - 1) * (p - 1)
        BigInteger euler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // e
        BigInteger e = BigInteger.valueOf(257);
        // d
        BigInteger d = e.modInverse(euler);
        Key publicKey = new Key(e, n);
        Key privateKey = new Key(d, n);
        this.keyPair = new KeyPair(publicKey, privateKey);
        return keyPair;
    }

    /**
     * 加解密信息共有调用方法
     * @param m
     * @return
     */
    private byte[] doFinal(BigInteger m, Key key){
        return m.modPow(key.getExponent(), key.getModulus()).toByteArray();
    }

    /**
     * 加密信息
     * @param message
     * @param key
     * @return
     */
    public byte[] encrypt(byte[] message, Key key){
        BigInteger bigInteger = new BigInteger(message);
        if (bigInteger.compareTo(key.getModulus()) == 1)
            throw new IllegalArgumentException("信息过长，请进行分组处理，或者选择更大的位数");
        return doFinal(bigInteger, key);
    }

    /**
     * 解密信息
     * @param message
     * @param key
     * @return
     */
    public byte[] decrypt(byte[] message, Key key){
        return this.doFinal(new BigInteger(message), key);
    }



}
