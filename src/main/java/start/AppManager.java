package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class AppManager extends Application {

    @Getter
    @Setter
    private static int count = 0;

    @Getter
    @Setter
    private static String usersEMail;

    @Getter
    private static Stage stage;

    @Getter
    @Setter
    private static String finishTextOfCourse;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Page1.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/Main.css");
        primaryStage.setScene(scene);
        primaryStage.maxHeightProperty().setValue(450);
        primaryStage.minHeightProperty().setValue(450);
        primaryStage.maxWidthProperty().setValue(600);
        primaryStage.minWidthProperty().setValue(600);
        primaryStage.setTitle("Miracle Test");
        primaryStage.show();
    }
}
