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
        String sql = "INSERT INTO drugs (drug_name, drug_code, description, price, qty_purchased, qty_in_stock, manDate, expDate, supplier_name, supplier_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        String name = rs.getString("drug_name");
        String code = rs.getString("drug_code");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        int initQuantity = rs.getInt("qty_purchased");
        int currentQuantity = rs.getInt("qty_in_stock");
        String manDate = rs.getString("manDate");
        String expDate = rs.getString("expDate");
        String supplierName = rs.getString("supplier_name");
        String supplierLocation = rs.getString("supplier_location");

        // Since suppliers are embedded in the drug table, include them directly
        SupplierLinkedList suppliers = new SupplierLinkedList();
        suppliers.addSupplier(supplierName, supplierLocation);

        return new Drug(name, code, description, price, manDate, expDate, initQuantity, currentQuantity, suppliers);
    }
}
