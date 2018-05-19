package com.lingnan.controller;

import com.lingnan.command.SignCommand;
import com.lingnan.utils.IOUtils;
import com.lingnan.view.DialogView;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/10.
 */
public class SignController extends BaseController{

    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea outputTextArea;

    @FXML
    private PasswordField publicKeyField;


    private SignCommand command = new SignCommand();

    private DialogView dialogView = new DialogView();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 点击签名事件处理方法
     */
    @FXML
    private void handleSign(){
        String text = this.inputTextArea.getText();
        String formattedKey = this.publicKeyField.getText();
        if (text == null || text.equals(""))
            return;
        Task<String> task = this.command.getSignTask(text, formattedKey);
        task.setOnSucceeded(event ->
            this.outputTextArea.setText(task.getValue()));
    }

    /**
     * 导入txt文件文件内容
     */
    @FXML
    private void handleOpenFile() throws IOException {
        File file = this.dialogView.openTextDialog(this.stage);
        if (file == null) return;
        String text = IOUtils.read(file);
        this.inputTextArea.setText(text);
    }
}
