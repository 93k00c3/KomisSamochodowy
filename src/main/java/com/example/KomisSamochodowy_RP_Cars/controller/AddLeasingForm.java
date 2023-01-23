package com.example.KomisSamochodowy_RP_Cars.controller;

import com.example.KomisSamochodowy_RP_Cars.model.Leasing;
import com.example.KomisSamochodowy_RP_Cars.service.EgzemplarzService;
import com.example.KomisSamochodowy_RP_Cars.service.KlientService;
import com.example.KomisSamochodowy_RP_Cars.service.LeasingService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddLeasingForm {

    public static void displayAddLeasingForm(){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wypelnij dane w celu dodania leasingu: ");
        window.setMinWidth(500);

        Label label1 = new Label("ID klienta, np.: \"5\"");
        RestrictiveTextField textField1 = new RestrictiveTextField("[id]");
        textField1.setMaxLength(3);
        Label label2 = new Label("ID egzemplarza, np.: \"5\"");
        RestrictiveTextField textField2 = new RestrictiveTextField("[id]");
        textField2.setMaxLength(3);
        Label label3 = new Label("Data początek, np.: \"2000-10-10\"");
        RestrictiveTextField textField3 = new RestrictiveTextField("size10");
        textField3.setMaxLength(10);
        Label label4 = new Label("Data koniec, np.: \"2000-10-10\"");
        RestrictiveTextField textField4 = new RestrictiveTextField("size10");
        textField4.setMaxLength(10);
        Label label5 = new Label("Opłata miesięczna, np.: \"500\"");
        RestrictiveTextField textField5 = new RestrictiveTextField("size4");
        textField5.setMaxLength(4);

        Button submitButton = new Button("Dodaj");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                    int klient_id = -1;
                    int egzemplarz_id = -1;
                    String data_poczatekStr = null;
                    String data_koniecStr = null;
                    double oplata_miesieczna = 0;

                    LeasingService leasingService = new LeasingService();
                    KlientService klientService = new KlientService();
                    EgzemplarzService egzemplarzService = new EgzemplarzService();

                    try {
                         klient_id = Integer.parseInt(textField1.getText());
                         egzemplarz_id = Integer.parseInt(textField2.getText());
                         data_poczatekStr = textField3.getText();
                         LocalDate data_poczatek = LocalDate.parse(data_poczatekStr);
                         data_koniecStr = textField4.getText();
                         LocalDate data_koniec = LocalDate.parse(data_koniecStr);
                         oplata_miesieczna = Double.parseDouble(textField5.getText());

                         leasingService.saveLeasing(new Leasing(klientService.getKlientById(klient_id),
                                                                egzemplarzService.getEgzemplarzById(egzemplarz_id),
                                                                data_poczatek, data_koniec, oplata_miesieczna
                                                                ));

                    }catch (DataException | DateTimeParseException | NumberFormatException exception){
                        BadInput.wyswietl("Błąd danych wejściowych (Exception)", "Zmień dane na wzór podanych...");
                    }
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
                                    submitButton, buttonInAlert);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}