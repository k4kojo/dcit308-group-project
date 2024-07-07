import pharmacy_db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugDAO {
    public void addDrug(String name, String code) {
        String sql = "INSERT INTO drugs (name, code) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchDrugs() {
        String sql = "SELECT * FROM drugs";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                System.out.println("ID: " + id + ", Name: " + name + ", Code: " + code);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
