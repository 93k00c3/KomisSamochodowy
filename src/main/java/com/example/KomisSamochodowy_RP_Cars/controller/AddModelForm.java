package com.example.KomisSamochodowy_RP_Cars.controller;

import com.example.KomisSamochodowy_RP_Cars.model.Model;
import com.example.KomisSamochodowy_RP_Cars.service.ModelService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.exception.DataException;

import java.time.format.DateTimeParseException;

public class AddModelForm {

    public static void displayAddModelForm(){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wypelnij dane w celu dodania modelu: ");
        window.setMinWidth(500);

        Label label1 = new Label("Marka, np.: \"Nissan\"");
        RestrictiveTextField textField1 = new RestrictiveTextField("size15");
        textField1.setMaxLength(15);

        Label label2 = new Label("Model, np.: \"Micra\"");
        RestrictiveTextField textField2 = new RestrictiveTextField("size15");
        textField2.setMaxLength(15);

        Label label3 = new Label("Typ nadwozia, np.: \"Hatchback\"");
        RestrictiveTextField textField3 = new RestrictiveTextField("size15");
        textField3.setMaxLength(15);


        Button submitButton = new Button("Dodaj");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                ModelService modelService = new ModelService();

                String marka = null;
                String model = null;
                String typ_nadwozia = null;

                try {
                     marka = textField1.getText();
                     model = textField2.getText();
                     typ_nadwozia = textField3.getText();

                    Model modelSamochodu = new Model(marka, model, typ_nadwozia);
                    modelService.saveModel(modelSamochodu);
                    window.close();

                }catch (DataException | DateTimeParseException | NumberFormatException exception){
                    BadInput.wyswietl("Błąd danych wejściowych (Exception)", "Zmień dane na wzór podanych...");
                }
            }
        });

        Button buttonInAlert = new Button("Anuluj");
        buttonInAlert.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, textField1, label2, textField2,label3, textField3, submitButton, buttonInAlert);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}