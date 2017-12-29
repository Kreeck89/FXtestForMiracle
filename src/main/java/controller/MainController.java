package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/miracle";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CREATE_USER = "INSERT INTO miracle.testedUsers (name, surname, email, aboutYou) VALUES (?,?,?,?)";

    @FXML
    private JFXButton btnStart;
    @FXML
    private JFXTextArea txtAboutYou;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtSurname;
    @FXML
    private JFXTextField txtName;
    @FXML
    private Text titleText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> registerAndStart());
    }

    private void registerAndStart() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtSurname.getText());
            preparedStatement.setString(3, txtEmail.getText());
            preparedStatement.setString(4, txtAboutYou.getText());
            preparedStatement.execute();
            connection.commit();
            AppManager.setUsersEMail(txtEmail.getText());
            txtName.clear();
            txtSurname.clear();
            txtEmail.clear();
            txtAboutYou.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        startTest();
    }

    private void startTest() {
        try {
            AppManager.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Page2.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
