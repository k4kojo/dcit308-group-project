package com.example.dcit308group8.pharmacy_db;

import com.example.dcit308group8.Drug;
import com.example.dcit308group8.SupplierLinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pharmacy_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Replace with your MySQL root password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    public void saveDrug(String name, String code, String description, double price, int initQuantity, int currentQuantity, String manufactureDate, String expiryDate, String supplierName, String supplierLocation) {
        String sql = "INSERT INTO drugs (Name, Code, Description, Price, Init Qty, In Stock, Man Date, Exp Date, Supplier Name, Supplier Loc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, code);
            stmt.setString(3, description);
            stmt.setDouble(4, price);
            stmt.setInt(5, initQuantity);
            stmt.setInt(6, currentQuantity);
            stmt.setString(7, manufactureDate);
            stmt.setString(8, expiryDate);
            stmt.setString(9, supplierName);
            stmt.setString(10, supplierLocation);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Drug> fetchDrugs() {
        String sql = "SELECT * FROM drugs";
        ObservableList<Drug> drugs = FXCollections.observableArrayList();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Drug drug = createDrugFromResultSet(rs);
                drugs.add(drug);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    public ObservableList<Drug> fetchDrugsBySearch(String searchText) {
        String sql = "SELECT * FROM drugs WHERE drug_name LIKE ? OR drug_code LIKE ?";
        ObservableList<Drug> drugs = FXCollections.observableArrayList();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, "%" + searchText + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Drug drug = createDrugFromResultSet(rs);
                    drugs.add(drug);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    private Drug createDrugFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("Name");
        String code = rs.getString("Code");
        String description = rs.getString("Description");
        double price = rs.getDouble("Price");
        int initQuantity = rs.getInt("Init Qty");
        int currentQuantity = rs.getInt("In Stock");
        String manDate = rs.getString("Man Date");
        String expDate = rs.getString("Exp Date");
        String supplierName = rs.getString("Supplier Name");
        String supplierLocation = rs.getString("Supplier Loc.");

        // Since suppliers are embedded in the drug table, include them directly
        SupplierLinkedList suppliers = new SupplierLinkedList();
        suppliers.addSupplier(supplierName, supplierLocation);

        return new Drug(name, code, description, price, manDate, expDate, initQuantity, currentQuantity, suppliers);
    }

    public static void updateStockQuantity(String drugCode, int quantitySold) {
        String query = "UPDATE drugs SET in_stock = in_stock - ? WHERE drug_code = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quantitySold);
            statement.setString(2, drugCode);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Updating stock quantity failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}
