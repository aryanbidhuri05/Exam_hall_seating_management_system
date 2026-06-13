package dao;

import model.Exam;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    public boolean addExam(Exam exam) throws SQLException {
        String sql = "INSERT INTO exams(exam_name, subject_name, department, semester, exam_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getExamName());
            ps.setString(2, exam.getSubjectName());
            ps.setString(3, exam.getDepartment());
            ps.setInt(4, exam.getSemester());
            ps.setString(5, exam.getExamDate());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Exam> getAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams ORDER BY exam_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                exams.add(new Exam(
                        rs.getInt("id"),
                        rs.getString("exam_name"),
                        rs.getString("subject_name"),
                        rs.getString("department"),
                        rs.getInt("semester"),
                        rs.getString("exam_date")
                ));
            }
        }
        return exams;
    }
}
