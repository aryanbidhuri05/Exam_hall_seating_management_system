package dao;

import model.Seating;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatingDAO {
    public void clearSeating() throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DELETE FROM seating");
        }
    }

    public boolean addSeating(Seating seating) throws SQLException {
        String sql = "INSERT INTO seating(student_name, roll_number, department, semester, exam_name, hall_number, seat_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seating.getStudentName());
            ps.setString(2, seating.getRollNumber());
            ps.setString(3, seating.getDepartment());
            ps.setInt(4, seating.getSemester());
            ps.setString(5, seating.getExamName());
            ps.setString(6, seating.getHallNumber());
            ps.setString(7, seating.getSeatNumber());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Seating> getAllSeating() throws SQLException {
        List<Seating> list = new ArrayList<>();
        String sql = "SELECT * FROM seating ORDER BY hall_number, seat_number";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Seating(
                        rs.getInt("id"),
                        rs.getString("student_name"),
                        rs.getString("roll_number"),
                        rs.getString("department"),
                        rs.getInt("semester"),
                        rs.getString("exam_name"),
                        rs.getString("hall_number"),
                        rs.getString("seat_number")
                ));
            }
        }
        return list;
    }
}
