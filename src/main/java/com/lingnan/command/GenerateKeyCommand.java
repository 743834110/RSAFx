package com.lingnan.command;

import com.lingnan.sdk.algorithms.RSA.KeyPair;
import com.lingnan.sdk.algorithms.RSA.RSA;
import com.lingnan.utils.R;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/18.
 */
public class GenerateKeyCommand extends BaseCommand{

    private ExecutorService service = Executors.newSingleThreadExecutor();
    private RSA rsa = RSA.getInstance();
    /**
     * 生成密钥对任务
     * @param bitLength 生成的比特位数
     * @return
     */
    public Task<KeyPair> getKeyPairTask(int bitLength){
        Task<KeyPair> task = new Task<KeyPair>() {
            @Override
            protected KeyPair call() throws Exception {
                KeyPair keyPair = rsa.generateKeyPair(bitLength);
                return keyPair;
            }
        };
        this.service.execute(task);
        return task;
    }
}
