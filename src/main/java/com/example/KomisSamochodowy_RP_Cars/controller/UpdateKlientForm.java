package com.example.KomisSamochodowy_RP_Cars.controller;

import com.example.KomisSamochodowy_RP_Cars.model.Klient;
import com.example.KomisSamochodowy_RP_Cars.service.KlientService;
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

public class UpdateKlientForm {
    public static void displayUpdateKlientForm(Klient klient){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wypelnij dane w celu dodania modelu: ");
        window.setMinWidth(500);

        Label label1 = new Label("Imie: np.: \"Rafał\"");
        RestrictiveTextField textField1 = new RestrictiveTextField("size_15");
        textField1.setMaxLength(15);

        Label label2 = new Label("Nazwisko: np.: \"Kolasa\"");
        RestrictiveTextField textField2 = new RestrictiveTextField("size_20");
        textField2.setMaxLength(20);

        Label label3 = new Label("Data urodzenia: np.: \"2000-10-10\"");
        RestrictiveTextField textField3 = new RestrictiveTextField("[DATE]");

        Label label4 = new Label("PESEL: np.: \"00302307395\"");
        RestrictiveTextField textField4 = new RestrictiveTextField("size_11");
        textField4.setMaxLength(11);

        Label label5 = new Label("Numer dowodu: np.: \"CHF350\"");
        RestrictiveTextField textField5 = new RestrictiveTextField("size_6");
        textField5.setMaxLength(6);

        Label label6 = new Label("Email: np.: \"sample_email@gmail.com\"");
        RestrictiveTextField textField6 = new RestrictiveTextField("size_25");
        textField6.setMaxLength(25);

        Label label7 = new Label("Telefon: np.: \"123123123\"");
        RestrictiveTextField textField7 = new RestrictiveTextField("size_9");
        textField7.setMaxLength(9);

        Label label8 = new Label("Miejscowosc: np.: \"Rzeszow\"");
        RestrictiveTextField textField8 = new RestrictiveTextField("size_25");
        textField8.setMaxLength(25);

        Label label9 = new Label("Kod pocztowy: np.: \"35-317\"");
        RestrictiveTextField textField9 = new RestrictiveTextField("size_6");
        textField9.setMaxLength(6);

        Label label10 = new Label("Ulica: np.: \"Krakowska\"");
        RestrictiveTextField textField10 = new RestrictiveTextField("size_25");
        textField10.setMaxLength(25);

        Label label11 = new Label("Numer domu: np.: \"15\"");
        RestrictiveTextField textField11 = new RestrictiveTextField("[NUMER DOMU]");

        Label label12 = new Label("Firma: np.: \"F.U.H ADAM KOWALSKI\"");
        RestrictiveTextField textField12 = new RestrictiveTextField("size_9");

        Label label13 = new Label("NIP: np.: \"1234567890\"");
        RestrictiveTextField textField13 = new RestrictiveTextField("size_10");
        textField13.setMaxLength(10);


        Button submitButton = new Button("Zmień");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                KlientService klientService = new KlientService();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                String imie = null;
                String nazwisko = null;
                String data_urodzeniaStr = null;
                String pesel = null;
                String numer_dowodu = null;
                String email = null;
                String telefon = null;
                String miejscowosc = null;
                String kod_pocztowy = null;
                String ulica = null;
                int numer_domu = 0;
                String firma = null;
                String nip = null;

                try {
                    imie = textField1.getText();
                    nazwisko = textField2.getText();
                    data_urodzeniaStr = textField3.getText();
                    LocalDate data_urodzenia = LocalDate.parse(data_urodzeniaStr);
                    pesel = textField4.getText();
                    numer_dowodu = textField5.getText();
                    email = textField6.getText();
                    telefon = textField7.getText();
                    miejscowosc = textField8.getText();
                    kod_pocztowy = textField9.getText();
                    ulica = textField10.getText();
                    numer_domu = Integer.parseInt(textField11.getText());
                    firma = textField12.getText();
                    nip = textField13.getText();

                    klient.setImie(imie);
                    klient.setNazwisko(nazwisko);
                    klient.setData_urodzenia(data_urodzenia);
                    klient.setPesel(pesel);
                    klient.setNumer_dowodu(numer_dowodu);
                    klient.setEmail(email);
                    klient.setTelefon(telefon);
                    klient.setMiejscowosc(miejscowosc);
                    klient.setKod_pocztowy(kod_pocztowy);
                    klient.setUlica(ulica);
                    klient.setNumer_domu(numer_domu);
                    klient.setFirma(firma);
                    klient.setNip(nip);

                    klientService.updateKlient(klient);
                    window.close();

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
                                    label6, textField6,
                                    label7, textField7,
                                    label8, textField8,
                                    label9, textField9,
                                    label10, textField10,
                                    label11, textField11,
                                    label12, textField12,
                                    label13, textField13,
                                    submitButton, buttonInAlert);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
