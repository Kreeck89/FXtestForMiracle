package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThirdPageController implements Initializable {

    @FXML
    private JFXCheckBox cb7;
    @FXML
    private JFXCheckBox cb6;
    @FXML
    private JFXCheckBox cb5;
    @FXML
    private JFXCheckBox cb4;
    @FXML
    private JFXCheckBox cb3;
    @FXML
    private JFXCheckBox cb2;
    @FXML
    private JFXCheckBox cb1;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Text titleText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNext.setOnAction(event -> countAnswerVal());
    }

    private void countAnswerVal() {
        if (cb1.isSelected()) {
            AppManager.setCount(AppManager.getCount() + 1);
        }
        if (cb2.isSelected()) {
            AppManager.setCount(AppManager.getCount() - 1);
        }
        if (cb3.isSelected()) {
            AppManager.setCount(AppManager.getCount() - 1);
        }
        if (cb4.isSelected()) {
            AppManager.setCount(AppManager.getCount() + 1);
        }
        if (cb5.isSelected()) {
            AppManager.setCount(AppManager.getCount() - 1);
        }
        if (cb6.isSelected()) {
            AppManager.setCount(AppManager.getCount() + 1);
        }
        if (cb7.isSelected()) {
            AppManager.setCount(AppManager.getCount() + 1);
        }
        System.out.println(AppManager.getCount());
        callNextPageTest();
    }

    private void callNextPageTest() {
        try {
            AppManager.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Page4.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
