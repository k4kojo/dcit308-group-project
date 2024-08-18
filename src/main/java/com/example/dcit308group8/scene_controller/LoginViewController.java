package com.example.dcit308group8.scene_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    public void initialize() {
        // Set event handler for Enter key on both fields
        userName.setOnAction(event -> validateAndOpenScreen());
        userPassword.setOnAction(event -> validateAndOpenScreen());
    }

    private void validateAndOpenScreen() {
        String name = userName.getText();
        String password = userPassword.getText();

        if (isValid(name, password)) {
            openDashboardView();
        } else {
            showAlert("Invalid Input", "Please enter a valid name and password.");
        }
    }

    private boolean isValid(String name, String password) {
        // Simple validation logic: Checks if fields are not empty
        return name != null && !name.trim().isEmpty() && password != null && !password.trim().isEmpty();
    }

    private void openDashboardView() {
        try {
            // Load the dashboard-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dcit308group8/dashboard-view.fxml"));
            Parent dashboardView = loader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) userName.getScene().getWindow();
            Scene scene = new Scene(dashboardView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Loading Error", "Failed to load the dashboard view.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
