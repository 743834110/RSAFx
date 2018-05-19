package com.lingnan.command;

import com.lingnan.sdk.algorithms.RSA.Key;
import com.lingnan.sdk.algorithms.RSA.KeyPair;
import com.lingnan.sdk.algorithms.RSA.RSA;
import com.lingnan.sdk.algorithms.SHA.SHA_1;
import com.lingnan.utils.Hex;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/12.
 */
public class ValidateCommand {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private SHA_1 sha = new SHA_1();
    private RSA rsa = RSA.getInstance();


    /**
     * 获取验证签名任务
     * @param message
     * @return 返回true 表示签名一致 返回false表示签名不一致 返回
     */
    public Task<Boolean> getValidateTask(String message, String formattedKey){
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                int length = message.length();
                if (message.length() <= 40)
                    return null;
                int index = message.lastIndexOf(":");
                String text = message.substring(0, index);
                String digest = message.substring(index + 1, length);
                Key key = Key.valueOf(formattedKey);
                digest = new String(rsa.decrypt(Hex.decode(digest), key));
                String temp = sha.digest(text.getBytes());
                if (digest.equals(temp)) {
                    this.updateMessage(digest);
                    return true;
                }
                return false;
            }
        };
        this.executorService.execute(task);
        return task;
    }
}
