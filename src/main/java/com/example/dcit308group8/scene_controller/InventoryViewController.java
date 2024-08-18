package com.example.dcit308group8.scene_controller;

import com.example.dcit308group8.pharmacy_db.DatabaseConnection;
import com.example.dcit308group8.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryViewController implements Initializable {

    @FXML
    private TextField search_drug_field;

    @FXML
    private TableView<Drug> drugTableView;

    @FXML
    private TableColumn<Drug, Integer> idColumn;

    @FXML
    private TableColumn<Drug, String> nameColumn;

    @FXML
    private TableColumn<Drug, String> codeColumn;

    @FXML
    private TableColumn<Drug, String> descriptionColumn;

    @FXML
    private TableColumn<Drug, Double> priceColumn;

    @FXML
    private TableColumn<Drug, Integer> initQuantityColumn;

    @FXML
    private TableColumn<Drug, Integer> currentQuantityColumn;

    @FXML
    private TableColumn<Drug, String> manDateColumn;

    @FXML
    private TableColumn<Drug, String> expDateColumn;

    @FXML
    private TableColumn<Drug, String> supplierNameColumn;

    @FXML
    private Button search_btn, addDrug_btn, return_btn;

    private ObservableList<Drug> drugList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        initQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("initQuantity"));
        currentQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("currentQuantity"));
        manDateColumn.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        expDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        // Load all drugs into the TableView when the controller is initialized
        loadAllDrugs();
    }

    @FXML
    private void handleAddNewDrug(ActionEvent event) {
        try {
            // Load the FXML file for the pop-up
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/dcit308group8/add-drug-view.fxml"));
            Parent parent = fxmlLoader.load();

            // Create a new stage for the pop-up window
            Stage stage = new Stage();
            stage.setTitle("Add New Drug");
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            stage.setScene(new Scene(parent));
            stage.showAndWait(); // Show the pop-up and wait for it to close

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDashboardViewController(ActionEvent event) {
        // Navigate back to the dashboard
        // You can implement the code to switch to the dashboard view here
    }

    @FXML
    public void handleSearchDrug(ActionEvent event) {
        // Implement search functionality
        String searchText = search_drug_field.getText();
        if (searchText != null && !searchText.trim().isEmpty()) {
            // Fetch and display filtered drugs based on the search term
            DatabaseConnection dbConnection = new DatabaseConnection();
            drugList.clear();
            drugList.addAll(dbConnection.fetchDrugsBySearch(searchText));
            drugTableView.setItems(drugList);
        } else {
            loadAllDrugs(); // Load all drugs if the search field is empty
        }
    }

    public void loadAllDrugs() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        drugList.clear();
        drugList.addAll(dbConnection.fetchDrugs());
        drugTableView.setItems(drugList);
    }
}
