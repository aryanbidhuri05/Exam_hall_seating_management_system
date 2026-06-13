package dao;

import model.Shift;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO {
    public boolean addShift(Shift shift) throws SQLException {
        String sql = "INSERT INTO shifts(shift_name, start_time, end_time) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, shift.getShiftName());
            ps.setString(2, shift.getStartTime());
            ps.setString(3, shift.getEndTime());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Shift> getAllShifts() throws SQLException {
        List<Shift> shifts = new ArrayList<>();
        String sql = "SELECT * FROM shifts ORDER BY shift_name";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                shifts.add(new Shift(
                        rs.getInt("id"),
                        rs.getString("shift_name"),
                        rs.getString("start_time"),
                        rs.getString("end_time")
                ));
            }
        }
        return shifts;
    }
}
