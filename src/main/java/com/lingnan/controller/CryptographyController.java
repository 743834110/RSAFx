package com.lingnan.controller;

import com.lingnan.command.CryptographyCommand;
import com.lingnan.sdk.algorithms.RSA.RSA;
import com.lingnan.utils.Hex;
import com.lingnan.utils.IOUtils;
import com.lingnan.view.DialogView;
import com.sun.deploy.security.ValidationState;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/5/10.
 */
public class CryptographyController extends BaseController{

    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private PasswordField publicKeyField;
    @FXML
    private PasswordField privateKeyField;

    private CryptographyCommand command = new CryptographyCommand();

    private DialogView dialogView = new DialogView();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Task<byte[]> handleCryptography(byte[] text ,RSA.Type type, String formattedKey) throws UnsupportedEncodingException {
        Task<byte[]> task = this.command.getComputeTask(text, formattedKey, type);
        return task;
    }
    /**
     * 处理使用公钥加解密的按钮点击事件
     */
    @FXML
    private void handleEncryptEvent() throws UnsupportedEncodingException {
        String text = this.inputTextArea.getText();
        if (text == null || text.equals(""))
            return;
        String formattedKey = this.publicKeyField.getText();
        if (formattedKey.equals(""))
            return;
        Task<byte[]> task = this.handleCryptography(Base64.getEncoder().encode(text.getBytes()), RSA.Type.PUBLIC_KEY, formattedKey);
        task.setOnSucceeded(event ->
                this.outputTextArea.setText(
                        new String(Base64.getEncoder().encode(task.getValue()))));
        task.setOnFailed(event -> {
            this.dialogView.showInformationDialog(task.getException().getMessage());
            this.inputTextArea.clear();
        });
    }

    /**
     * 处理使用私钥加解密的按钮点击事件
     */
    @FXML
    private void handleDecryptEvent() throws UnsupportedEncodingException {
        String text = this.outputTextArea.getText();
        this.inputTextArea.clear();//清空inputTextArea中的内容
        if (text == null || text.equals(""))
            return;
        String formattedKey = this.privateKeyField.getText();
        if (formattedKey.equals(""))
            return;
        Task<byte[]> task = this.handleCryptography(Base64.getDecoder().decode(text), RSA.Type.PRIVATE_KEY, formattedKey);
        task.setOnSucceeded(event ->
                this.inputTextArea.setText(
                        new String(Base64.getDecoder().decode(task.getValue()))));
    }

    /**
     * 打开文件
     */
    @FXML
    private void handleOpenFile() throws IOException {
        File file = this.dialogView.openTextDialog(this.stage);
        if (file == null)
            return;
        String text = IOUtils.read(file);
        this.inputTextArea.setText(text);
    }

    /**
     * 保存文件
     */
    @FXML
    private void handleSaveFile() throws IOException {
        File file = this.dialogView.openSaveFileDialog(this.stage);
        if (file == null) return;
        String text = this.outputTextArea.getText();
        IOUtils.write(file, text);
    }


}
