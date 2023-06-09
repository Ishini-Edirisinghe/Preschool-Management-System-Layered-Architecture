package lk.ijse.preschool.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.preschool.bo.BOFactory;
import lk.ijse.preschool.bo.costom.SkillStatusBO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardWindowController implements Initializable {
    @FXML
    private AnchorPane dashboardAnchorPane;

    @FXML
    private AnchorPane dashboardMainAnchorPane;

    @FXML
    private Label lblGreeting;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private BarChart<String,Number> barChartSkills;
    private SkillStatusBO skillStatusBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SKILLSTATUS);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minute from the current time
        int hour = currentTime.getHour();
        //    int minute = currentTime.getMinute();

        // Display a greetings message based on the time of day
        String greetingsMessage;
        if (hour >= 0 && hour < 12) {
            lblGreeting.setText("Good Morning !!!");
        } else if (hour >= 12 && hour < 17) {
            lblGreeting.setText("Good Afternoon !!!");
        } else {
            lblGreeting.setText("Good evening !!!");
        }
        dateTimeInit();

        //Charts initialize

        XYChart.Series seriesExcellent = new XYChart.Series<>();
        seriesExcellent.setName("Excellent");

        XYChart.Series seriesGood = new XYChart.Series<>();
        seriesGood.setName("Good");

        XYChart.Series seriesWeak = new XYChart.Series<>();
        seriesWeak.setName("Weak");

       try {

            String[] subjectNames = { "counting", "crafting", "drawing", "reading", "singing", "writing" };

            for (int i = 0; i < subjectNames.length; i++) {
                String subject = subjectNames[i];

                int excellentCount = skillStatusBO.getStatusCount(subject,"Excellent");
                int goodCount = skillStatusBO.getStatusCount(subject,"Good");
                int weakCount = skillStatusBO.getStatusCount(subject,"Weak");

                seriesExcellent.getData().add(new XYChart.Data<>(subject, excellentCount));
                seriesGood.getData().add(new XYChart.Data<>(subject, goodCount));
                seriesWeak.getData().add(new XYChart.Data<>(subject, weakCount));

            }

            } catch(SQLException | ClassNotFoundException throwables){
                throwables.printStackTrace();
            }

        barChartSkills.getData().addAll(seriesExcellent, seriesGood, seriesWeak);

    }

    private void dateTimeInit() {
        //set time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")))));
        lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //set date
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }

    private void setUI(String location) throws IOException { //less boiler plate code

        Parent load = FXMLLoader.load(getClass().getResource(location));
        dashboardAnchorPane.getChildren().clear();
        dashboardAnchorPane.getChildren().add(load);

    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader= new FXMLLoader(LoginWindowController.class.getResource("/view/dashboard-window-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        thisStage.setScene(scene);
        thisStage.setMaximized(true);

    }

    @FXML
    void btnEventOnAction(ActionEvent event) throws IOException {
        setUI("/view/event-window-view.fxml");
    }

    @FXML
    void btnManageStudentOnAction(ActionEvent event) throws IOException {
       setUI("/view/manage-student-window-view.fxml");
    }
    @FXML
        void btnPaymentOnAction(ActionEvent event) throws IOException {
        setUI("/view/payments-window-view.fxml");
    }

    @FXML
    void btnManageTeacherOnAction(ActionEvent event) throws IOException {
    setUI("/view/manage-teacher-window-view.fxml");
    }
    @FXML
    void btnSyllabusOnAction(ActionEvent event) throws IOException {
       setUI("/view/syllabus-window-view.fxml");
    }
    @FXML
    void btnStudentSkillsStatusOnAction(ActionEvent event) throws IOException {
       setUI("/view/student-skill-status-window-view.fxml");
    }
    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardMainAnchorPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login-window-view.fxml"))));

        stage.centerOnScreen();
        stage.show();
    }


}

