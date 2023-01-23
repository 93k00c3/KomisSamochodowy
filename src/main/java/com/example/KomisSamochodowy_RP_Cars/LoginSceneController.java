package com.example.KomisSamochodowy_RP_Cars;

import com.example.KomisSamochodowy_RP_Cars.controller.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginSceneController {

    @FXML
    TextField passwordTextField;

    private static String password = "admin";

    private Stage stage;
    private Scene scene;

    public void login(javafx.event.ActionEvent actionEvent) throws IOException {
        if(passwordTextField.getText().equals(password)) {
            Parent root = FXMLLoader.load(getClass().getResource("headScene.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else AlertBox.wyswietl("BAD PASSWORD", "Bledne haslo. Podaj poprawne.");
    }
}
