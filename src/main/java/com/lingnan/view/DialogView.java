package com.lingnan.view;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/12.
 */
public class DialogView extends BaseView{

    /**
     * 上次被打开的文件
     */
    private File openedFile = null;

    //文件过滤器类型
    public interface ExtensionFilter{
        FileChooser.ExtensionFilter DEFAULT = new FileChooser.ExtensionFilter("ALL"
                , "*.*");
        FileChooser.ExtensionFilter TXT_FILTER = new FileChooser.ExtensionFilter("TXT files(*.txt)"
                , "*.txt");

    }

    public enum FileChooserType{
        SINGLE_SELECT_DIALOG,
        MULTIPLE_SELECT_DIALOG,
        SAVE_DIALOG
    }

    private List<File> openFileChooser(FileChooserType type , Stage stage, FileChooser.ExtensionFilter... filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(this.openedFile);
        fileChooser.getExtensionFilters().addAll(filters);
        if (type == FileChooserType.SINGLE_SELECT_DIALOG)
            return Arrays.asList(fileChooser.showOpenDialog(stage));
        else if (type == FileChooserType.SINGLE_SELECT_DIALOG)
            return fileChooser.showOpenMultipleDialog(stage);
        else
            return Arrays.asList(fileChooser.showSaveDialog(stage));
    }

    /**
     * 打开文件
     * @return 返回txt文件File对象
     */
    public File openTextDialog(Stage stage){

        List<File> files = this.openFileChooser(FileChooserType.SINGLE_SELECT_DIALOG, stage,
                ExtensionFilter.TXT_FILTER);
        if (files.size() == 0)
            return null;
        File file = files.get(0);
        return file;
    }

    /**
     * 获取保存的文件名
     * @param stage
     * @return
     */
    public File openSaveFileDialog(Stage stage){
        File file = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(ExtensionFilter.TXT_FILTER);
        file = fileChooser.showSaveDialog(stage);
        return file;
    }

    /**
     * 显示消息对话框
     */
    public void showInformationDialog(String information){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, information);
        alert.showAndWait();
    }
}
