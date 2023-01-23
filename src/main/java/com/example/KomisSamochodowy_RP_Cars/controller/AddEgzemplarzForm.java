package com.example.KomisSamochodowy_RP_Cars.controller;

import com.example.KomisSamochodowy_RP_Cars.model.Egzemplarz;
import com.example.KomisSamochodowy_RP_Cars.service.EgzemplarzService;
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

public class AddEgzemplarzForm {

    public static void displayAddEgzemplarzForm(){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wypelnij dane w celu dodania modelu: ");
        window.setMinWidth(500);

        Label label1 = new Label("ID Modelu, np.: \"Micra\"");
        RestrictiveTextField textField1 = new RestrictiveTextField("size15");
        textField1.setMaxLength(15);
        Label label2 = new Label("Rok produkcji, np.: \"2000\"");
        RestrictiveTextField textField2 = new RestrictiveTextField("size4");
        textField2.setMaxLength(5);
        Label label3 = new Label("Pojemność silnika, np.: \"1400\"");
        RestrictiveTextField textField3 = new RestrictiveTextField("size5");
        textField3.setMaxLength(5);
        Label label4 = new Label("Przebieg, np.: \"240000\"");
        RestrictiveTextField textField4 = new RestrictiveTextField("size15");
        textField4.setMaxLength(15);
        Label label5 = new Label("Rodzaj paliwa, np.: \"Benzyna\"");
        RestrictiveTextField textField5 = new RestrictiveTextField("size15");
        textField5.setMaxLength(15);
        Label label6 = new Label("Kolor, np.: \"Niebieski\"");
        RestrictiveTextField textField6 = new RestrictiveTextField("size15");
        textField6.setMaxLength(15);
        Label label7 = new Label("VIN, np.: \"1A2S3D4F5G6H7J8K9\"");
        RestrictiveTextField textField7 = new RestrictiveTextField("size17");
        textField7.setMaxLength(17);
        Label label8 = new Label("Dostępność, np.: \"true\"");
        RestrictiveTextField textField8 = new RestrictiveTextField("size5");
        textField8.setMaxLength(5);
        Label label9 = new Label("Cena, np.: \"6000\"");
        RestrictiveTextField textField9 = new RestrictiveTextField("size6");
        textField9.setMaxLength(6);

        Button submitButton = new Button("Dodaj");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                int idModelu = -1;
                int rok_produkcji = 0;
                int pojemnoscSilnika = 0;
                int przebieg = 0;
                String rodzaj_paliwa = null;
                String kolor = null;
                String vin = null;
                boolean dostepnosc;
                int cena = 0;

                ModelService modelService = new ModelService();
                EgzemplarzService egzemplarzService = new EgzemplarzService();

                idModelu = Integer.parseInt(textField1.getText());
                rok_produkcji = Integer.parseInt(textField2.getText());
                pojemnoscSilnika = Integer.parseInt(textField3.getText());
                przebieg = Integer.parseInt(textField4.getText());
                rodzaj_paliwa = textField5.getText();
                kolor = textField6.getText();
                vin = textField7.getText();
                dostepnosc = Boolean.parseBoolean(textField8.getText());
                cena = Integer.parseInt(textField9.getText());

                egzemplarzService.saveEgzemplarz(new Egzemplarz(modelService.getModelById(idModelu), rok_produkcji, pojemnoscSilnika, przebieg, rodzaj_paliwa, kolor, vin, dostepnosc, cena));
                window.close();
            }
        });

        Button buttonInAlert = new Button("Anuluj");
        buttonInAlert.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, textField1,
                                    label2, textField2,
                                    label3, textField3,
                                    label4, textField4,
                                    label5, textField5,
                                    label6, textField6,
                                    label7, textField7,
                                    label8, textField8,
                                    label9, textField9,
                                    submitButton, buttonInAlert);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}