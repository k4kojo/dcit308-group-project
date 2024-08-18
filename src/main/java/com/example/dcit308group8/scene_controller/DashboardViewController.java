package com.example.dcit308group8.scene_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardViewController {

    @FXML
    private Button purchases_btn, sale_btn, account_btn, inventory_btn, logout_btn;

    @FXML
    private void onPurchasesViewController() {
        navigateTo("/com/example/dcit308group8/purchases-view.fxml", purchases_btn);
    }

    @FXML
    private void onSaleViewController() {
        navigateTo("/com/example/dcit308group8/sales-view.fxml", sale_btn);
    }

    @FXML
    private void onAccountViewController() {
        navigateTo("/com/example/dcit308group8/account-view.fxml", account_btn);
    }

    @FXML
    private void onInventoryViewController() {
        navigateTo("/com/example/dcit308group8/inventory-view.fxml", inventory_btn);
    }

    @FXML
    private void onLoginViewController() {
        navigateTo("/com/example/dcit308group8/login-view.fxml", logout_btn);
    }

    /*@FXML
    private void onUtilitiesViewController() {
        navigateTo("/com/example/dcit308group8/view/utilities-view.fxml", sale_btn1);
    }*/

   /* @FXML
    private void onRecordsViewController() {
        navigateTo("/com/example/dcit308group8/view/records-view.fxml", sale_btn111);
    }*/

   /* @FXML
    private void onSettingsViewController() {
        navigateTo("/com/example/dcit308group8/view/settings-view.fxml", sale_btn1111);
    }*/

    private void navigateTo(String fxmlFilePath, Node node) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
