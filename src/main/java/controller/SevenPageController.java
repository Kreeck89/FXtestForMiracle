package controller;

import com.jfoenix.controls.JFXButton;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import start.AppManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SevenPageController extends Application implements Initializable {

    @FXML
    private PieChart chartIQ;
    @FXML
    private JFXButton btnLink;
    @FXML
    private Text finishText;
    @FXML
    private Text titleText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showKnowliegeDiagram();

        finishText.setText(AppManager.getFinishTextOfCourse());

        btnLink.setOnAction(event -> goToMiracleLink());
    }

    private void goToMiracleLink() {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument("https://www.facebook.com/miracle.it.group/");
    }

    private void showKnowliegeDiagram() {
        int n = 11;
        ObservableList<PieChart.Data> values = FXCollections.observableArrayList();
        n = n - AppManager.getCount();
        PieChart.Data yourIQ = new PieChart.Data("Your LvL", AppManager.getCount());
        PieChart.Data leftIQ = new PieChart.Data("Not right answers", n);
        values.addAll(yourIQ, leftIQ);
        chartIQ.setData(values);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
