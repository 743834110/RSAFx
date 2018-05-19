package com.lingnan.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/11.
 */
public abstract class BaseController implements Initializable{

    protected Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(URL location, ResourceBundle resources) {
    }
}
