package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, roll_number, department, semester) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNumber());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getSemester());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, roll_number=?, department=?, semester=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNumber());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getSemester());
            ps.setInt(5, student.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("department"),
                        rs.getInt("semester")
                ));
            }
        }
        return students;
    }

    public List<Student> searchStudents(String query) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR roll_number LIKE ? OR department LIKE ? ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + query + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("roll_number"),
                            rs.getString("department"),
                            rs.getInt("semester")
                    ));
                }
            }
        }
        return students;
    }
}
