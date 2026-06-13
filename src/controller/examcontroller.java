package controller;

import dao.ExamDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Exam;

import java.sql.SQLException;

public class ExamController {
    @FXML private TextField examNameField;
    @FXML private TextField subjectField;
    @FXML private TextField departmentField;
    @FXML private TextField semesterField;
    @FXML private DatePicker examDatePicker;
    @FXML private TableView<Exam> examTable;
    @FXML private TableColumn<Exam, String> examNameCol;
    @FXML private TableColumn<Exam, String> subjectCol;
    @FXML private TableColumn<Exam, String> deptCol;
    @FXML private TableColumn<Exam, Integer> semCol;
    @FXML private TableColumn<Exam, String> dateCol;

    private final ExamDAO examDAO = new ExamDAO();
    private final ObservableList<Exam> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        examNameCol.setCellValueFactory(new PropertyValueFactory<>("examName"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        semCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        examTable.setItems(data);
        refreshExams();
    }

    @FXML
    private void addExam() {
        if (!validate()) return;
        try {
            Exam exam = new Exam(
                    0,
                    examNameField.getText().trim(),
                    subjectField.getText().trim(),
                    departmentField.getText().trim(),
                    Integer.parseInt(semesterField.getText().trim()),
                    examDatePicker.getValue().toString()
            );
            examDAO.addExam(exam);
            refreshExams();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Save Error", e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        examNameField.clear();
        subjectField.clear();
        departmentField.clear();
        semesterField.clear();
        examDatePicker.setValue(null);
    }

    private boolean validate() {
        if (examNameField.getText() == null || examNameField.getText().trim().length() < 2) return invalid("Invalid exam name.");
        if (subjectField.getText() == null || subjectField.getText().trim().length() < 2) return invalid("Invalid subject name.");
        if (departmentField.getText() == null || !departmentField.getText().trim().matches("[A-Za-z ]{2,30}")) return invalid("Invalid department.");
        if (examDatePicker.getValue() == null) return invalid("Select exam date.");
        try {
            int sem = Integer.parseInt(semesterField.getText().trim());
            if (sem < 1 || sem > 10) return invalid("Semester must be between 1 and 10.");
        } catch (Exception e) {
            return invalid("Semester must be numeric.");
        }
        return true;
    }

    private boolean invalid(String msg) {
        showAlert(Alert.AlertType.WARNING, "Validation", msg);
        return false;
    }

    private void refreshExams() {
        try {
            data.setAll(examDAO.getAllExams());
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
