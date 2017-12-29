package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import start.AppManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FourthPageController implements Initializable {

    private static final String VIDEO_PATH = "/Users/ozzy/IdeaProjects/FXtestForMiracle/src/main/resources/video/JavaSEInterface.mp4";
    String absolutPath = new File(VIDEO_PATH).getAbsolutePath();
    Media media = new Media(new File(absolutPath).toURI().toString());
    MediaPlayer player = new MediaPlayer(media);

    @FXML
    private JFXCheckBox cb3;
    @FXML
    private JFXCheckBox cb2;
    @FXML
    private JFXCheckBox cb1;
    @FXML
    private Text textQuestion;
    @FXML
    private MediaView mediaView;
    @FXML
    private Text titleText;
    @FXML
    private JFXButton btnNext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        play();
        btnNext.setOnAction(event -> countAnswerVal());
    }

    private void countAnswerVal() {
        if (cb1.isSelected()) {
            AppManager.setCount(AppManager.getCount() - 1);
        }
        if (cb2.isSelected()) {
            AppManager.setCount(AppManager.getCount() + 1);
        }
        if (cb3.isSelected()) {
            AppManager.setCount(AppManager.getCount() - 1);
        }
        callNextPageTest();
    }

    private void callNextPageTest() {
        player.stop();
        try {
            AppManager.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Page5.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        player.setAutoPlay(true);
        mediaView.setMediaPlayer(player);
    }
}
