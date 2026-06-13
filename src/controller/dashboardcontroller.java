package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private BorderPane rootPane;

    @FXML
    public void initialize() {
        loadCenter("/fxml/students.fxml");
    }

    @FXML
    private void openStudents() {
        loadCenter("/fxml/students.fxml");
    }

    @FXML
    private void openExams() {
        loadCenter("/fxml/exams.fxml");
    }

    @FXML
    private void openShifts() {
        loadCenter("/fxml/shifts.fxml");
    }

    @FXML
    private void openSeating() {
        loadCenter("/fxml/seating.fxml");
    }

    @FXML
    private void logout() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCenter(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            rootPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
