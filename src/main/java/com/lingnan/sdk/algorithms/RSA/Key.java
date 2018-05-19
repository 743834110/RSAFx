package com.lingnan.sdk.algorithms.RSA;


import java.math.BigInteger;
import java.util.Base64;

/**
 * Created by Administrator on 2018/5/7.
 */
public class Key {
    /**
     * 指数
     */
    private BigInteger exponent;
    /**
     * 乘积
     */
    private BigInteger modulus;

    Key(BigInteger exponent, BigInteger modulus) {
        this.exponent = exponent;
        this.modulus = modulus;
    }

    public BigInteger getExponent() {
        return exponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    @Override
    public String toString() {
        String string = "";
        string += this.exponent.toString(16);
        string += ",";
        string += this.modulus.toString(16);
        string = new String(Base64.getEncoder().encode(string.getBytes()));
        return string;
    }

    /**
     * 通过格式化的字符串生成密钥
     * @param formattedString
     * @return
     */
    public static Key valueOf(String formattedString){
        String string = new String(Base64.getDecoder().decode(formattedString));
        if (string.indexOf(',') == -1)
            return null;
        String[] splits = string.split(",");
        BigInteger exponent = new BigInteger(splits[0], 16);
        BigInteger modulus = new BigInteger(splits[1], 16);
        Key key = new Key(exponent,modulus);
        return key;
    }

}
