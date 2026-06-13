package controller;

import dao.ExamDAO;
import dao.SeatingDAO;
import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Exam;
import model.Seating;
import model.Student;

import java.sql.SQLException;
import java.util.*;

public class SeatingController {
    @FXML private TextField examNameField;
    @FXML private TextField hallsField;
    @FXML private TextField capacityPerHallField;
    @FXML private TableView<Seating> seatingTable;
    @FXML private TableColumn<Seating, String> studentCol;
    @FXML private TableColumn<Seating, String> rollCol;
    @FXML private TableColumn<Seating, String> deptCol;
    @FXML private TableColumn<Seating, Integer> semCol;
    @FXML private TableColumn<Seating, String> examCol;
    @FXML private TableColumn<Seating, String> hallCol;
    @FXML private TableColumn<Seating, String> seatCol;

    private final SeatingDAO seatingDAO = new SeatingDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final ExamDAO examDAO = new ExamDAO();
    private final ObservableList<Seating> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        semCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        examCol.setCellValueFactory(new PropertyValueFactory<>("examName"));
        hallCol.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        seatCol.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        seatingTable.setItems(data);
        refreshTable();
    }

    @FXML
    private void autofillExamName() {
        try {
            List<Exam> exams = examDAO.getAllExams();
            if (!exams.isEmpty()) {
                examNameField.setText(exams.get(0).getExamName());
            }
        } catch (SQLException ignored) {
        }
    }

    @FXML
    private void generateSeating() {
        if (!validate()) return;
        try {
            int hallCount = Integer.parseInt(hallsField.getText().trim());
            int capacity = Integer.parseInt(capacityPerHallField.getText().trim());
            List<Student> students = studentDAO.getAllStudents();
            if (students.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No Data", "Add students before generating seating.");
                return;
            }

            List<Student> ordered = arrangeByDepartment(students);
            seatingDAO.clearSeating();

            int hallNo = 1;
            int seatNo = 1;
            int hallUsedCapacity = 0;

            for (Student s : ordered) {
                if (hallNo > hallCount) {
                    showAlert(Alert.AlertType.WARNING, "Insufficient Space", "Not enough halls for all students.");
                    break;
                }
                if (hallUsedCapacity >= capacity) {
                    hallNo++;
                    hallUsedCapacity = 0;
                    seatNo = 1;
                    if (hallNo > hallCount) break;
                }

                Seating seating = new Seating(
                        0,
                        s.getName(),
                        s.getRollNumber(),
                        s.getDepartment(),
                        s.getSemester(),
                        examNameField.getText().trim(),
                        "Hall-" + hallNo,
                        "S-" + seatNo
                );
                seatingDAO.addSeating(seating);
                hallUsedCapacity++;
                seatNo++;
            }
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Seating arrangement generated.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Generation Error", e.getMessage());
        }
    }

    private List<Student> arrangeByDepartment(List<Student> students) {
        Map<String, Queue<Student>> deptMap = new HashMap<>();
        for (Student s : students) {
            deptMap.computeIfAbsent(s.getDepartment(), k -> new LinkedList<>()).add(s);
        }
        List<Student> arranged = new ArrayList<>();
        String previousDept = "";

        while (!deptMap.isEmpty()) {
            String chosenDept = null;
            int max = -1;
            for (Map.Entry<String, Queue<Student>> entry : deptMap.entrySet()) {
                if (!entry.getKey().equals(previousDept) && !entry.getValue().isEmpty() && entry.getValue().size() > max) {
                    max = entry.getValue().size();
                    chosenDept = entry.getKey();
                }
            }

            if (chosenDept == null) {
                chosenDept = deptMap.keySet().iterator().next();
            }

            Student picked = deptMap.get(chosenDept).poll();
            arranged.add(picked);
            previousDept = chosenDept;
            if (deptMap.get(chosenDept).isEmpty()) {
                deptMap.remove(chosenDept);
            }
        }
        return arranged;
    }

    @FXML
    private void refreshTable() {
        try {
            data.setAll(seatingDAO.getAllSeating());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", e.getMessage());
        }
    }

    private boolean validate() {
        if (examNameField.getText() == null || examNameField.getText().trim().length() < 2) return invalid("Enter exam name.");
        try {
            int h = Integer.parseInt(hallsField.getText().trim());
            int c = Integer.parseInt(capacityPerHallField.getText().trim());
            if (h < 1 || c < 1) return invalid("Halls and capacity must be greater than 0.");
        } catch (Exception e) {
            return invalid("Halls and capacity must be numeric.");
        }
        return true;
    }

    private boolean invalid(String msg) {
        showAlert(Alert.AlertType.WARNING, "Validation", msg);
        return false;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
