package controller;

import dao.ShiftDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Shift;

import java.sql.SQLException;

public class ShiftController {
    @FXML private TextField shiftNameField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private TableView<Shift> shiftTable;
    @FXML private TableColumn<Shift, String> shiftNameCol;
    @FXML private TableColumn<Shift, String> startCol;
    @FXML private TableColumn<Shift, String> endCol;

    private final ShiftDAO shiftDAO = new ShiftDAO();
    private final ObservableList<Shift> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        shiftNameCol.setCellValueFactory(new PropertyValueFactory<>("shiftName"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        shiftTable.setItems(data);
        refreshShifts();
    }

    @FXML
    private void addShift() {
        if (!validate()) return;
        try {
            Shift shift = new Shift(0, shiftNameField.getText().trim(), startTimeField.getText().trim(), endTimeField.getText().trim());
            shiftDAO.addShift(shift);
            refreshShifts();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Save Error", e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        shiftNameField.clear();
        startTimeField.clear();
        endTimeField.clear();
    }

    private boolean validate() {
        if (shiftNameField.getText() == null || shiftNameField.getText().trim().length() < 2) return invalid("Invalid shift name.");
        if (startTimeField.getText() == null || !startTimeField.getText().trim().matches("[0-2][0-9]:[0-5][0-9]")) return invalid("Start time must be HH:mm.");
        if (endTimeField.getText() == null || !endTimeField.getText().trim().matches("[0-2][0-9]:[0-5][0-9]")) return invalid("End time must be HH:mm.");
        return true;
    }

    private boolean invalid(String msg) {
        showAlert(Alert.AlertType.WARNING, "Validation", msg);
        return false;
    }

    private void refreshShifts() {
        try {
            data.setAll(shiftDAO.getAllShifts());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", e.getMessage());
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
