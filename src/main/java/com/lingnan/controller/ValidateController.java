package com.lingnan.controller;

import com.lingnan.command.ValidateCommand;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/10.
 */
public class ValidateController extends BaseController{
    @FXML
    private TextArea signedTextArea;
    @FXML
    private TextArea rowTextArea;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private PasswordField privateKeyField;

    private ValidateCommand command = new ValidateCommand();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 处理验证签名点击事件
     */
    @FXML
    private void handleValidate(){
        String signedText = this.signedTextArea.getText();
        String formattedKey = this.privateKeyField.getText();
        if (signedText == null || signedText.equals(""))
            return;
        Task<Boolean> task = this.command.getValidateTask(signedText, formattedKey);
        task.setOnFailed(event -> {
            task.getException().printStackTrace();
        });
        task.setOnSucceeded(event -> {
            Boolean match = task.getValue();
            if (match == null)
                this.rowTextArea.setText("带验证信息非法");
            else if (match){
                this.rowTextArea.setText(task.getMessage());
                this.resultTextArea.setText("验证通过");
            }
            else {
                this.rowTextArea.clear();
                this.resultTextArea.setText("验证失败");
            }
        });



    }
}
