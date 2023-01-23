package com.example.KomisSamochodowy_RP_Cars;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
            stage.setTitle("R&P CARS");
            Scene scene1 = new Scene(root, 800, 600);
            stage.setScene(scene1);
            Rectangle2D primaryScreenBounds =
                    Screen.getScreens().get(0).getVisualBounds();
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}