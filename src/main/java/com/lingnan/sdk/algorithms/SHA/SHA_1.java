package com.lingnan.sdk.algorithms.SHA;

import javafx.beans.property.IntegerProperty;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/5/7.
 */
public class SHA_1 {

    private Logger logger = Logger.getAnonymousLogger();

    /**
     * MD缓存
     */
    private final static int H0 = 0x67452301;
    private final static int H1 = 0xEFCDAB89;
    private final static int H2 = 0x98BADCFE;
    private final static int H3 = 0x10325476;
    private final static int H4 = 0xC3D2E1F0;

    private int A;
    private int B;
    private int C;
    private int D;
    private int E;

    /**
     * 比特字W
     */
    private int[] w;

    /**
     * 常量K数组
     */
    private final static int[] K = new int[]{
            0x5A827999,
            0x6ED9EBA1,
            0x8F1BBCDC,
            0xCA62C1D6
    };

    private int[] message;

    /**
     * 消息填充
     */
    private void fill(byte[] msg){
        // 求分组个数
        int bitLength = ((msg.length * 8 + 64) / 512 + 1);
        int bit = bitLength * 512;
        this.message = new int[bit / 32];
        // 0填充
        Arrays.fill(this.message, 0);
        int temp;
        for (int i = 0; i < msg.length; i++) {
            temp = 24 - ((i & 0x03) << 3 );
            this.message[i / 4] |= (msg[i] & 0X000000ff) << temp;
        }
        temp = 24 - ((msg.length & 0x03) << 3 );
        //比特字串1填充
        this.message[msg.length / 4] |= 0X80 << temp;
        //附加消息长度:消息长度为int类型，当消息长度超出int类型的范围时,出来的哈希值将不可用
        this.message[bit / 32 - 1] = msg.length * 8;
    }

    /**
     * 初始化MD缓存
     */
    private void initMDCache(){
        this.A = H0;
        this.B = H1;
        this.C = H2;
        this.D = H3;
        this.E = H4;
    }

    /**
     * 验证签名
     * @return 返回true 表示签名一致, 否则签名不一致
     */
    public boolean validate(){
        return false;
    }
    /**
     * 产生信息散列值
     * @param msg 待签名信息
     * @return
     */
    public String digest(byte[] msg){

        this.fill(msg);
        this.initMDCache();

        //分组个数
        int groupSum = this.message.length * 32 / 512;
        this.logger.info("分组数：" + groupSum);
        //该循环每次产生512比特分组
        for (int i = 0; i < groupSum; i++) {
            int[] group = new int[16];
            System.arraycopy(this.message, i * 16, group, 0, 16);
            this.groupProcess(group);
        }


        return String.format("%08X%08X%08X%08X%08X", this.A, this.B, this.C, this.D, this.E);
    }

    private void log(int[] msg){
        String result = "";
        for (int a: msg){
            result += String.format("%08X", a);
        }
        this.logger.info(result);
    }
    private void log(int msg){
        String result = String.format("%08X", msg);
        this.logger.info(result);
    }
    /**
     * 初始化比特字W
     * 长度为80
     * 前16个字为当前分组的前16个字
     */
    private void initW(int[] group){
        this.w = new int[80];
        System.arraycopy(group, 0, this.w, 0, 16);
        for (int i = 16; i < 80; i++) {
            this.w[i] = this.w[i - 16] ^ this.w[i - 14] ^ this.w[i - 8] ^ this.w[i - 3];
            this.w[i] = this.cycleLeftShift(this.w[i], 1);
        }
    }

    /**
     * 循环左移
     * @param component 将要被循环左移的元素
     * @return
     */
    private int cycleLeftShift(int component, int n){
        return (component << n) | (component >>> 32 - n);
    }



    /**
     * 输出基本逻辑函数
     * @param t 当前循环次数
     * @return
     */
    private int getBaseLogicFunction(int t){
        int f = 0;
        switch (t / 20) {
            case 0:
                f = (this.B & this.C) | (~this.B & this.D);
                break;
            case 1:
                f = this.B ^ this.C ^ this.D;
                break;
            case 2:
                f = (this.B & this.C) | (this.B & this.D) | (this.C & this.D);
                break;
            case 3:
                f = this.B ^ this.C ^ this.D;
                break;
        }
        return f;
    }

    /**
     * int => long 转换
     * 用于数据运算
     * @param data
     * @return
     */
    private long shiftTransmit(int data){
        return Long.valueOf(String.format("%08X", data ), 16);
    }
    private int getTruncateInt(int... data){
        long temp = 0;
        for (int elem: data)
            temp += this.shiftTransmit(elem);
        return (int) temp;
    }
    /**
     * 针对每个512比特分组进行处理并得出哈希值
     * @param group
     * @return
     */
    private void groupProcess(int[] group){
        int[] before = new int[]{
               this.A, this.B, this.C, this.D, this.E
        };
        this.initW(group);
        for (int i = 0; i < 80; i++) {
            int temp = this.getTruncateInt(this.E, this.getBaseLogicFunction(i),
                    this.cycleLeftShift(this.A, 5), this.w[i], K[i / 20]);
            this.E = this.D;
            this.D = this.C;
            this.C = this.cycleLeftShift(this.B, 30);
            this.B = this.A;
            this.A = temp;
        }
        this.A = this.getTruncateInt(this.A, before[0]);
        this.B = this.getTruncateInt(this.B, before[1]);
        this.C = this.getTruncateInt(this.C, before[2]);
        this.D = this.getTruncateInt(this.D, before[3]);
        this.E = this.getTruncateInt(this.E, before[4]);
    }
}
