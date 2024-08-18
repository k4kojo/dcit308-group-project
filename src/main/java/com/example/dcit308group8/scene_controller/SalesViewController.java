package com.example.dcit308group8.scene_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesViewController {

    @FXML
    private Button print_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button return_btn;

    @FXML
    private TextArea receiptArea;

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private TextField unitPriceField;

    @FXML
    private TextField discountField;

    @FXML
    private TextField netPriceField;

    @FXML
    private void initialize() {
        // Initialize your UI components here, if necessary
    }

    @FXML
    private void handlePrintAction(ActionEvent event) {
        // Handle the print button action here
        System.out.println("Print Button Clicked");
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        // Handle the cancel button action here
        System.out.println("Cancel Button Clicked");
    }

    @FXML
    public void onDashboardViewController(ActionEvent event) throws IOException {
        try {
            // Load the Dashboard view from FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dcit308group8/dashboard-view.fxml"));
            Parent dashboardView = loader.load();

            // Get the current stage (window) from the return button
            Stage stage = (Stage) return_btn.getScene().getWindow();

            // Set the scene to the dashboard view
            Scene dashboardScene = new Scene(dashboardView);
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

    @FXML
    private void handleNumberButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String number = clickedButton.getText();
        // Handle number button clicks (1, 2, 3, etc.)
        System.out.println("Number " + number + " Clicked");
    }

    @FXML
    private void handlePaymentAction(ActionEvent event) {
        // Handle the payment button action here
        System.out.println("Payment Button Clicked");
    }
}
