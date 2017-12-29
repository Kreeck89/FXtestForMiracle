package controller;

import com.jfoenix.controls.JFXButton;
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
import java.sql.*;
import java.util.ResourceBundle;

public class SixPageController implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/miracle";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String USER_UPDATE = "UPDATE testedUsers SET courseID = ? WHERE email = ?";

    private static final String GET_COURSE_ID = "SELECT * FROM testedUsers WHERE email = ?";
    private static final String WHAT_COURSE = "SELECT * FROM course WHERE id = ?";

    private static final String VIDEO_PATH = "/Users/ozzy/IdeaProjects/FXtestForMiracle/src/main/resources/video/FinishVideo.mp4";
    private static final String ABSOLUT_PATH = new File(VIDEO_PATH).getAbsolutePath();
    Media media = new Media(new File(ABSOLUT_PATH).toURI().toString());
    MediaPlayer player = new MediaPlayer(media);

    @FXML
    private JFXButton finish;
    @FXML
    private JFXButton btnStop;
    @FXML
    private JFXButton btnPause;
    @FXML
    private JFXButton btnPlay;
    @FXML
    private MediaView mvFinish;
    @FXML
    private Text titleText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnPlay.setOnAction(event -> startVideo());
        btnPause.setOnAction(event -> pauseVideo());
        btnStop.setOnAction(event -> stopVideo());
        finish.setOnAction(event -> callNextPageTest());
    }

    private void callNextPageTest() {
        stopVideo();
        updateCourseId();
        int id = gettCourseId();
        AppManager.setFinishTextOfCourse("Your recommended course is: " + WhatValueYourCourse(id));

        try {
            AppManager.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Page7.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCourseId() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE)) {
            connection.setAutoCommit(false);
            if (AppManager.getCount() >= 9) {
                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, AppManager.getUsersEMail());
                preparedStatement.execute();
            } else if (AppManager.getCount() < 9 & AppManager.getCount() >= 7) {
                preparedStatement.setInt(1, 2);
                preparedStatement.setString(2, AppManager.getUsersEMail());
                preparedStatement.execute();
            } else {
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, AppManager.getUsersEMail());
                preparedStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void stopVideo() {
        player.stop();
    }

    private void pauseVideo() {
        player.pause();
    }

    private void startVideo() {
        mvFinish.setMediaPlayer(player);
        player.play();
    }

    private String WhatValueYourCourse(int id) {
        String course = "";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(WHAT_COURSE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    private int gettCourseId() {
        int id = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COURSE_ID)) {
            preparedStatement.setString(1, AppManager.getUsersEMail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("courseID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(id);
        return id;
    }
}
