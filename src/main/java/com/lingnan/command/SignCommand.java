package com.lingnan.command;

import com.lingnan.sdk.algorithms.RSA.Key;
import com.lingnan.sdk.algorithms.RSA.KeyPair;
import com.lingnan.sdk.algorithms.RSA.RSA;
import com.lingnan.sdk.algorithms.SHA.SHA_1;
import com.lingnan.utils.Hex;
import javafx.concurrent.Task;

import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/12.
 */
public class SignCommand {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    //sha算法实现类
    private SHA_1 sha = new SHA_1();
    //rsa算法实现类
    private RSA rsa = RSA.getInstance();


    /**
     * 根据信息进行签名并将签名附加
     * 到原本信息的后面
     * @param message
     * @return
     */
    public Task<String> getSignTask(String message, String formattedString){
        Task<String> task = new Task<String>() {

            protected String call() throws Exception {
                String digest = SignCommand.this.sha.digest(message.getBytes());
                Key key = Key.valueOf(formattedString);
                byte [] bytes = SignCommand.this.rsa.encrypt(digest.getBytes(), key);
                digest = message + ":" + Hex.encode(bytes);
                return digest;
            }
        };
        this.executorService.execute(task);
        return task;
    }
}
