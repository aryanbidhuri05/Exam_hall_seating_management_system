package controller;

import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentController {
    @FXML private TextField nameField;
    @FXML private TextField rollField;
    @FXML private TextField departmentField;
    @FXML private TextField semesterField;
    @FXML private TextField searchField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameCol;
    @FXML private TableColumn<Student, String> rollCol;
    @FXML private TableColumn<Student, String> deptCol;
    @FXML private TableColumn<Student, Integer> semCol;

    private final StudentDAO studentDAO = new StudentDAO();
    private final ObservableList<Student> data = FXCollections.observableArrayList();
    private Student selectedStudent;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        semCol.setCellValueFactory(new PropertyValueFactory<>("semester"));

        studentTable.setItems(data);
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedStudent = newVal;
            if (newVal != null) {
                nameField.setText(newVal.getName());
                rollField.setText(newVal.getRollNumber());
                departmentField.setText(newVal.getDepartment());
                semesterField.setText(String.valueOf(newVal.getSemester()));
            }
        });
        refreshStudents();
    }

    @FXML
    private void addStudent() {
        if (!validateInputs()) return;
        try {
            Student student = new Student(0, nameField.getText().trim(), rollField.getText().trim(), departmentField.getText().trim(), Integer.parseInt(semesterField.getText().trim()));
            studentDAO.addStudent(student);
            refreshStudents();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    @FXML
    private void updateStudent() {
        if (selectedStudent == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Required", "Select a student to update.");
            return;
        }
        if (!validateInputs()) return;
        try {
            selectedStudent.setName(nameField.getText().trim());
            selectedStudent.setRollNumber(rollField.getText().trim());
            selectedStudent.setDepartment(departmentField.getText().trim());
            selectedStudent.setSemester(Integer.parseInt(semesterField.getText().trim()));
            studentDAO.updateStudent(selectedStudent);
            refreshStudents();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Update Error", e.getMessage());
        }
    }

    @FXML
    private void deleteStudent() {
        if (selectedStudent == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Required", "Select a student to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected student?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try {
                    studentDAO.deleteStudent(selectedStudent.getId());
                    refreshStudents();
                    clearFields();
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Delete Error", e.getMessage());
                }
            }
        });
    }

    @FXML
    private void searchStudents() {
        try {
            List<Student> list = studentDAO.searchStudents(searchField.getText().trim());
            data.setAll(list);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Search Error", e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        rollField.clear();
        departmentField.clear();
        semesterField.clear();
        selectedStudent = null;
        studentTable.getSelectionModel().clearSelection();
    }

    private boolean validateInputs() {
        if (nameField.getText() == null || !nameField.getText().trim().matches("[A-Za-z ]{2,50}")) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Enter a valid student name.");
            return false;
        }
        if (rollField.getText() == null || !rollField.getText().trim().matches("[A-Za-z0-9-]{3,20}")) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Enter a valid roll number.");
            return false;
        }
        if (departmentField.getText() == null || !departmentField.getText().trim().matches("[A-Za-z ]{2,30}")) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Enter a valid department.");
            return false;
        }
        try {
            int sem = Integer.parseInt(semesterField.getText().trim());
            if (sem < 1 || sem > 10) {
                showAlert(Alert.AlertType.WARNING, "Validation", "Semester must be between 1 and 10.");
                return false;
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Semester must be numeric.");
            return false;
        }
        return true;
    }

    private void refreshStudents() {
        try {
            data.setAll(studentDAO.getAllStudents());
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
