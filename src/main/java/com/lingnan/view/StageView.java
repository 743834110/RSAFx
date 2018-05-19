package com.lingnan.view;

import com.lingnan.controller.BaseController;
import com.lingnan.utils.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/5/11.
 */
public class StageView extends BaseView{


    /**
     * 构建最初窗口
     */
    public void buildPrimaryStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getResourceAsURL("fxml/rootLayout.fxml"));
        loader.setControllerFactory(param -> {
            BaseController controller = null;
            try {
                Object obj = param.newInstance();

                if (obj instanceof BaseController){
                    controller = (BaseController) obj;
                    controller.setStage(stage);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return controller;
        });
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
