// File: src/main/java/com/example/dcit308group8/scene_controller/AddDrugViewController.java

package com.example.dcit308group8.scene_controller;

import com.example.dcit308group8.pharmacy_db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDrugViewController {

    @FXML
    private TextField drugNameField, drugCodeField, drugDescriptionField, drugPriceField, drugQuantityField, drugManufactureDateField, drugExpiryDateField, supplierNameField, supplierLocationField;
    @FXML
    private Button save_btn, cancel_btn;

    // Handle the Save button click
    @FXML
    private void handleSaveDrug() {
        String name = drugNameField.getText();
        String code = drugCodeField.getText();
        String description = drugDescriptionField.getText();
        double price = Double.parseDouble(drugPriceField.getText());
        int initQuantity = Integer.parseInt(drugQuantityField.getText());
        int currentQuantity = Integer.parseInt(null);
        String manufactureDate = drugManufactureDateField.getText();
        String expiryDate = drugExpiryDateField.getText();
        String supplierName = supplierNameField.getText();
        String supplierLocation = supplierLocationField.getText();

        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.saveDrug(name, code, description, price, initQuantity, currentQuantity, manufactureDate, expiryDate, supplierName, supplierLocation);

        // Close the pop-up window after saving
        Stage stage = (Stage) drugNameField.getScene().getWindow();
        stage.close();
    }

    // Handle the Cancel button click
    @FXML
    private void handleCancelDrug() {
        // Close the pop-up window without saving
        Stage stage = (Stage) drugNameField.getScene().getWindow();
        stage.close();
    }
}
