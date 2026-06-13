package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText() == null ? "" : usernameField.getText().trim();
        String password = passwordField.getText() == null ? "" : passwordField.getText().trim();

        if ("admin".equals(username) && "admin123".equals(password)) {
            try {
                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(dashboardRoot, 1100, 700);
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
                stage.setScene(scene);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to open dashboard.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Use username: admin and password: admin123");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
