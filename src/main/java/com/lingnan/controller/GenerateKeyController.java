package com.lingnan.controller;

import com.lingnan.command.GenerateKeyCommand;
import com.lingnan.sdk.algorithms.RSA.KeyPair;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/17.
 */
public class GenerateKeyController extends BaseController {

    @FXML
    private ToggleGroup formatGroup;
    @FXML
    private ToggleGroup figureGroup;
    @FXML
    private TextArea privateKeyTextArea;
    @FXML
    private TextArea publicKeyTextArea;

    private GenerateKeyCommand command = new GenerateKeyCommand();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    /**
     * 密钥生成按钮点击事件处理方法
     */

    @FXML
    private void handleGenerateKey(){
        RadioButton figureRadioButton = (RadioButton) this.figureGroup.getSelectedToggle();
        String text = figureRadioButton.getText();
        Task<KeyPair> task = this.command.getKeyPairTask(Integer.valueOf(text));
        task.setOnSucceeded(event -> {
            KeyPair keyPair = task.getValue();
            this.publicKeyTextArea.setText(keyPair.getPublicKey().toString());
            this.privateKeyTextArea.setText(keyPair.getPrivateKey().toString());
        });
        task.setOnFailed(event -> {
            task.getException().printStackTrace();
        });

    }
}
