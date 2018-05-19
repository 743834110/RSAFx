package com.lingnan;/**
 * Created by Administrator on 2018/5/2.
 */

import com.lingnan.view.StageView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private StageView view = new StageView();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.view.buildPrimaryStage(primaryStage);
    }

}
