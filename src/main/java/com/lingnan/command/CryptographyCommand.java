package com.lingnan.command;

import com.lingnan.sdk.algorithms.RSA.Key;
import com.lingnan.sdk.algorithms.RSA.KeyPair;
import com.lingnan.sdk.algorithms.RSA.RSA;
import com.lingnan.utils.Hex;
import javafx.concurrent.Task;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/11.
 */
public class CryptographyCommand extends BaseCommand{

    private RSA rsa = RSA.getInstance();
    private KeyPair keyPair = null;

    private int bitLength ;
    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * 获取加解密文任务
     * @param text
     * @param flag
     * @return
     * @throws UnsupportedEncodingException
     */
    public Task<byte[]> getComputeTask(byte[] text, String formattedKey, RSA.Type flag) throws UnsupportedEncodingException {
        Task<byte[]> task = new Task<byte[]>() {
            @Override
            protected byte[] call() throws Exception {

                Key key = Key.valueOf(formattedKey);
                if (flag == RSA.Type.PUBLIC_KEY)
                    return rsa.encrypt(text, key);
                return rsa.decrypt(text, key);
            }
        };
        this.service.execute(task);
        return task;
    }
}
