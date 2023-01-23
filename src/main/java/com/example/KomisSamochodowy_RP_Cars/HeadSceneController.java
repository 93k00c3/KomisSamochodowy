package com.example.KomisSamochodowy_RP_Cars;

import com.example.KomisSamochodowy_RP_Cars.HibernateUtil.SingletonConnection;
import com.example.KomisSamochodowy_RP_Cars.controller.*;
import com.example.KomisSamochodowy_RP_Cars.model.*;
import com.example.KomisSamochodowy_RP_Cars.service.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class HeadSceneController implements Initializable {

    @FXML
    private TabPane myTabPane;

    /*
    |------- TO DO TAB -------|------- TO DO TAB -------|------- TO DO TAB -------|
    */
    @FXML
    private Tab todoTab;
    @FXML
    private RadioButton r1;
    @FXML
    private RadioButton r2;
    @FXML
    private TextField myTextField;
    @FXML
    private ListView<String> todayTasks;
    @FXML
    private ListView<String> tomorrowTasks;
    @FXML
    private ToggleGroup whichDay;
    @FXML
    private Label actualDateLabel;

    @FXML
    void addTo(ActionEvent event) {
        if(r1.isSelected()){
            if(!myTextField.getText().isEmpty()) todayTasks.getItems().add(myTextField.getText());
        }
        else if(r2.isSelected()){
            if(!myTextField.getText().isEmpty()) tomorrowTasks.getItems().add(myTextField.getText());
        }
        myTextField.clear();
    }

    @FXML
    void moveToDone(ActionEvent event){
        int index1 = todayTasks.getSelectionModel().getSelectedIndex();
        int index2 = tomorrowTasks.getSelectionModel().getSelectedIndex();
        if(index1>-1){
            todayTasks.getItems().remove(index1);
        }
        if(index2>-1){
            tomorrowTasks.getItems().remove(index2);
        }
    }
    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            actualDateLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /*
    |------- MODELE TAB -------|------- MODELE TAB -------|------- MODELE TAB -------|
    */
    @FXML
    private Tab modeleTab;
    @FXML
    private TableView<Model> modelTableView;
    @FXML
    private TableColumn<Model, Integer> idModelColumn;
    @FXML
    private TableColumn<Model, String> markaColumn;
    @FXML
    private TableColumn<Model, String> modelColumn;
    @FXML
    private TableColumn<Model, String> typNadwoziaColumn;

    @FXML
    Button addModelButton;
    @FXML
    Button removeModelButton;
    @FXML
    Button updateModelButton;

    @FXML
    void addModelToDataBase(ActionEvent event){
        AddModelForm.displayAddModelForm();
        modelObservableList = FXCollections.observableArrayList(modelService.getModelTable());
        modelTableView.setItems(modelObservableList);
        modelTableView.refresh();
    }

    @FXML
    void removeModelFromDataBase(ActionEvent event){
        Model model = modelTableView.getSelectionModel().getSelectedItem();
        int model_id = model.getId();
        //modelService.removeModel(model);

        session.beginTransaction();

        Query query = session.createSQLQuery("CALL MODEL_DEL(:id)")
                .addEntity(Model.class)
                .setParameter("id", model_id);
        query.executeUpdate();
        session.getTransaction().commit();


        modelObservableList = FXCollections.observableArrayList(modelService.getModelTable());
        modelTableView.setItems(modelObservableList);
        modelTableView.refresh();
    }

    @FXML
    void updateModelInDataBase(ActionEvent event){
        Model model = modelTableView.getSelectionModel().getSelectedItem();
        UpdateModelForm.displayUpdateModelForm(model);
        modelObservableList = FXCollections.observableArrayList(modelService.getModelTable());
        modelTableView.setItems(modelObservableList);
        modelTableView.refresh();

    }

    ModelService modelService = new ModelService();

    public ObservableList<Model> modelObservableList;


    /*
   |------ EGZEMPLARZE TAB ------|------ EGZEMPLARZE TAB ------|------ EGZEMPLARZE TAB ------|
   */
    @FXML
    private Tab egzemplarzeTab;
    @FXML
    private TableView<Egzemplarz> egzemplarzTableView;
    @FXML
    private TableColumn<Egzemplarz, Integer> idEgzemplarzColumn;
    @FXML
    private TableColumn<Egzemplarz, Integer> idModeluColumn;
    @FXML
    private TableColumn<Egzemplarz, Integer> rokProdukcjiColumn;
    @FXML
    private TableColumn<Egzemplarz, Integer> przebiegColumn;
    @FXML
    private TableColumn<Egzemplarz, Integer> pojemnoscSilnikaColumn;
    @FXML
    private TableColumn<Egzemplarz, String> rodzajPaliwaColumn;
    @FXML
    private TableColumn<Egzemplarz, String> kolorColumn;
    @FXML
    private TableColumn<Egzemplarz, String> vinColumn;
    @FXML
    private TableColumn<Egzemplarz, Boolean> dostepnoscColumn;
    @FXML
    private TableColumn<Egzemplarz, Integer> cenaColumn;
    @FXML
    Button addEgzemplarzButton;
    @FXML
    Button removeEgzemplarzButton;
    @FXML
    Button updateEgzemplarzButton;

    @FXML
    void addEgzemplarzToDataBase(ActionEvent event){
        AddEgzemplarzForm.displayAddEgzemplarzForm();
        egzemplarzObservableList = FXCollections.observableArrayList(egzemplarzService.getEgzemplarzTable());
        egzemplarzTableView.setItems(egzemplarzObservableList);
        egzemplarzTableView.refresh();
    }

    @FXML
    void removeEgzemplarzFromDataBase(ActionEvent event){
        Egzemplarz egzemplarz = egzemplarzTableView.getSelectionModel().getSelectedItem();
        egzemplarzService.removeEgzemplarz(egzemplarz);
        egzemplarzObservableList = FXCollections.observableArrayList(egzemplarzService.getEgzemplarzTable());
        egzemplarzTableView.setItems(egzemplarzObservableList);
        egzemplarzTableView.refresh();
    }

    @FXML
    void updateEgzemplarzInDataBase(ActionEvent event){
        Egzemplarz egzemplarz = egzemplarzTableView.getSelectionModel().getSelectedItem();
        UpdateEgzemplarzForm.displayUpdateEgzemplarzForm(egzemplarz);
        egzemplarzObservableList = FXCollections.observableArrayList(egzemplarzService.getEgzemplarzTable());
        egzemplarzTableView.setItems(egzemplarzObservableList);
        egzemplarzTableView.refresh();

    }

    EgzemplarzService egzemplarzService = new EgzemplarzService();

    public ObservableList<Egzemplarz> egzemplarzObservableList;


    /*
    |------- KLIENCI TAB -------|------- KLIENCI TAB -------|------- KLIENCI TAB -------|
    */
    @FXML
    private Tab klienciTab;
    @FXML
    private TableView<Klient> klientTableView;
    @FXML
    private TableColumn<Klient, Integer> idKlientColumn;
    @FXML
    private TableColumn<Klient, String> imieColumn;
    @FXML
    private TableColumn<Klient, String> nazwiskoColumn;
    @FXML
    private TableColumn<Klient, LocalDate> dataUrodzeniaColumn;
    @FXML
    private TableColumn<Klient, String> peselColumn;
    @FXML
    private TableColumn<Klient, String> numerDowoduColumn;
    @FXML
    private TableColumn<Klient, String> emailColumn;
    @FXML
    private TableColumn<Klient, String> telefonColumn;
    @FXML
    private TableColumn<Klient, String> miejscowoscColumn;
    @FXML
    private TableColumn<Klient, String> kodPocztowyColumn;
    @FXML
    private TableColumn<Klient, String> ulicaColumn;
    @FXML
    private TableColumn<Klient, Integer> numerDomuColumn;
    @FXML
    private TableColumn<Klient, String> firmaColumn;
    @FXML
    private TableColumn<Klient, String> nipColumn;

    @FXML
    Button addKlientButton;
    @FXML
    Button removeKlientButton;
    @FXML
    Button updateKlientButton;

    @FXML
    void addKlientToDataBase(ActionEvent event){
        AddKlientForm.displayAddKlientForm();
        List<Klient> klientTable = klientService.getKlientTable();
        klientObservableList = FXCollections.observableArrayList(klientTable);
        klientTableView.setItems(klientObservableList);
        klientTableView.refresh();

    }

    @FXML
    void removeKlientFromDataBase(ActionEvent event){
        Klient klient = klientTableView.getSelectionModel().getSelectedItem();
        klientService.removeKlient(klient);
        klientObservableList = FXCollections.observableArrayList(klientService.getKlientTable());
        klientTableView.setItems(klientObservableList);
        klientTableView.refresh();

    }

    @FXML
    void updateKlientInDataBase(ActionEvent event){
        Klient klient = klientTableView.getSelectionModel().getSelectedItem();
        UpdateKlientForm.displayUpdateKlientForm(klient);
        klientObservableList = FXCollections.observableArrayList(klientService.getKlientTable());
        klientTableView.setItems(klientObservableList);
        klientTableView.refresh();
    }

    public KlientService klientService = new KlientService();

    public ObservableList<Klient> klientObservableList;

    /*
    |------- LEASINGI TAB -------|------- LEASINGI TAB -------|------- LEASINGI TAB -------|
    */
    @FXML
    private Tab leasingiTab;
    @FXML
    private TableView<Leasing> leasingTableView;
    @FXML
    private TableColumn<Leasing, Integer> idLeasinguColumn;
    @FXML
    private TableColumn<Leasing, Integer> idKlientaColumn;
    @FXML
    private TableColumn<Leasing, Integer> idEgzemplarzaColumn;
    @FXML
    private TableColumn<Leasing, LocalDate> dataPoczatekColumn;
    @FXML
    private TableColumn<Leasing, LocalDate> dataKoncowaColumn;
    @FXML
    private TableColumn<Leasing, Double> oplataMiesiecznaColumn;

    @FXML
    Button addLeasingButton;
    @FXML
    Button removeLeasingButton;
    @FXML
    Button updateLeasingButton;

    @FXML
    void addLeasingToDataBase(ActionEvent event){
        AddLeasingForm.displayAddLeasingForm();
        leasingObservableList = FXCollections.observableArrayList(leasingService.getLeasingTable());
        leasingTableView.setItems(leasingObservableList);
        leasingTableView.refresh();

    }

    @FXML
    void removeLeasingFromDataBase(ActionEvent event){
        Leasing leasing = leasingTableView.getSelectionModel().getSelectedItem();
        leasingService.removeLeasing(leasing);
        leasingObservableList = FXCollections.observableArrayList(leasingService.getLeasingTable());
        leasingTableView.setItems(leasingObservableList);
        leasingTableView.refresh();
    }

    @FXML
    void updateLeasingInDataBase(ActionEvent event){
        Leasing leasing = leasingTableView.getSelectionModel().getSelectedItem();
        UpdateLeasingForm.displayUpdateLeasingForm(leasing);
        leasingObservableList = FXCollections.observableArrayList(leasingService.getLeasingTable());
        leasingTableView.setItems(leasingObservableList);
        leasingTableView.refresh();

    }

    public LeasingService leasingService = new LeasingService();

    public ObservableList<Leasing> leasingObservableList;

    /*
    |------- ZAKUPY TAB -------|------- ZAKUPY TAB -------|------- ZAKUPY TAB -------|
    */
    @FXML
    private Tab transakcjeKupnaTab;
    @FXML
    private TableView<Transakcja_kupna> transakcjeTableView;
    @FXML
    private TableColumn<Transakcja_kupna, Integer> idTransakcjiColumn;
    @FXML
    private TableColumn<Transakcja_kupna, Integer> idKlientaColumn1;
    @FXML
    private TableColumn<Transakcja_kupna, Integer> idEgzemplarzaColumn1;
    @FXML
    private TableColumn<Transakcja_kupna, LocalDate> dataZakupuColumn;
    @FXML
    private TableColumn<Transakcja_kupna, Integer> dlugoscGwarancjiColumn;

    @FXML
    Button addTransakcja_KupnaButton;
    @FXML
    Button removeTransakcja_KupnaButton;
    @FXML
    Button updateTransakcja_KupnaButton;

    @FXML
    void addTransakcjaToDataBase(ActionEvent event){
        AddTransakcjaForm.displayAddTransakcjaForm();
        transakcja_kupnaObservableList = FXCollections.observableArrayList(transakcja_kupnaService.getTransakcja_kupnaTable());
        transakcjeTableView.setItems(transakcja_kupnaObservableList);
        transakcjeTableView.refresh();

    }

    @FXML
    void removeTransakcjaFromDataBase(ActionEvent event){
        Transakcja_kupna transakcja_kupna = transakcjeTableView.getSelectionModel().getSelectedItem();
        transakcja_kupnaService.removeTransakcja_kupna(transakcja_kupna);
        transakcja_kupnaObservableList = FXCollections.observableArrayList(transakcja_kupnaService.getTransakcja_kupnaTable());
        transakcjeTableView.setItems(transakcja_kupnaObservableList);
        transakcjeTableView.refresh();

    }

    @FXML
    void updateTransakcjaInDataBase(ActionEvent event){
        Transakcja_kupna transakcja_kupna = transakcjeTableView.getSelectionModel().getSelectedItem();
        UpdateTransakcjaForm.displayUpdateTransakcjaForm(transakcja_kupna);
        transakcja_kupnaObservableList = FXCollections.observableArrayList(transakcja_kupnaService.getTransakcja_kupnaTable());
        transakcjeTableView.setItems(transakcja_kupnaObservableList);
        transakcjeTableView.refresh();
    }

    public Transakcja_kupnaService transakcja_kupnaService = new Transakcja_kupnaService();

    public ObservableList<Transakcja_kupna> transakcja_kupnaObservableList;

    /*
    |------- OPEN SESSION -------|------- OPEN SESSION -------|------- OPEN SESSION -------|
    */
    private static final SessionFactory sessionFactory = SingletonConnection.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    private Stage stage;
    private Scene scene;


    /*
    |------- LOGOUT BUTTON -------|------- LOGOUT BUTTON -------|------- LOGOUT BUTTON -------|
    */
    public void logout(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
   |------- DATA INITIALIZE COLUMN -------|------- DATA INITIALIZE COLUMN -------|------- DATA INITIALIZE COLUMN -------|
   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock();

        //---- MODELE INITIALIZE ----|---- MODELE INITIALIZE ----|---- MODELE INITIALIZE ----|
        idModelColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("id"));
        markaColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("marka"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("nazwa_modelu"));
        typNadwoziaColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("typ_nadwozia"));

        modelObservableList = FXCollections.observableArrayList(modelService.getModelTable());
        modelTableView.setItems(modelObservableList);

//        List<Model> list = new ArrayList<>();
//
//        session.beginTransaction();
//        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("MODEL_SEL");
//        query.execute();
//
//        list = query.getResultList();
//
//        modelObservableList = FXCollections.observableArrayList(list);
//        modelTableView.setItems(modelObservableList);
//        session.getTransaction();
//        session.close();


        //------- EGZEMPLARZ INITIALIZE -------|------- EGZEMPLARZ INITIALIZE -------|
        idEgzemplarzColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("id"));
        idModeluColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Egzemplarz,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Egzemplarz, Integer> param) {
                System.out.println(param.getValue());
                return new SimpleIntegerProperty(param.getValue().getModel().getId()).asObject();
            }
        });

        rokProdukcjiColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("rok_produkcji"));
        przebiegColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("przebieg"));
        rodzajPaliwaColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("rodzaj_paliwa"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("cena"));
        pojemnoscSilnikaColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("pojemnosc_silnika"));
        dostepnoscColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Boolean>("dostepnosc"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("vin"));
        kolorColumn.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("kolor"));

        egzemplarzObservableList = FXCollections.observableArrayList(egzemplarzService.getEgzemplarzTable());
        egzemplarzTableView.setItems(egzemplarzObservableList);

        //        List<Egzemplarz> list = new ArrayList<>();
        //
        //        session.beginTransaction();
        //        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("EGZEMPLARZ_SEL");
        //        query.execute();
        //
        //        list = query.getResultList();
        //
        //        egzemplarzObservableList = FXCollections.observableArrayList(list);
        //        egzemplarzTableView.setItems(egzemplarzObservableList);
        //        session.getTransaction();
        //        session.close();


        //--- KLIENCI INITIALIZE ---|--- KLIENCI INITIALIZE ---|--- KLIENCI INITIALIZE ---|
        idKlientColumn.setCellValueFactory(new PropertyValueFactory<Klient, Integer>("id"));
        imieColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("nazwisko"));
        dataUrodzeniaColumn.setCellValueFactory(new PropertyValueFactory<Klient, LocalDate>("data_urodzenia"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("pesel"));
        numerDowoduColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("numer_dowodu"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("email"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("telefon"));
        miejscowoscColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("miejscowosc"));
        kodPocztowyColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("kod_pocztowy"));
        ulicaColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("ulica"));
        numerDomuColumn.setCellValueFactory(new PropertyValueFactory<Klient, Integer>("numer_domu"));
        firmaColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("firma"));
        nipColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("nip"));

        klientObservableList = FXCollections.observableArrayList(klientService.getKlientTable());
        klientTableView.setItems(klientObservableList);

        //        List<Klient> list = new ArrayList<>();
        //
        //        session.beginTransaction();
        //        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("KLIENT_SEL");
        //        query.execute();
        //
        //        list = query.getResultList();
        //
        //        klientObservableList = FXCollections.observableArrayList(list);
        //        klientTableView.setItems(klientObservableList);
        //        session.getTransaction();
        //        session.close();


        //--- LEASINGI INITIALIZE ---|--- LEASINGI INITIALIZE ---|--- LEASINGI INITIALIZE ---|
        idLeasinguColumn.setCellValueFactory(new PropertyValueFactory<Leasing, Integer>("id"));
        idKlientaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Leasing,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Leasing, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getKlient().getId()).asObject();
            }
        });
        idEgzemplarzaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Leasing,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Leasing, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getEgzemplarz().getId()).asObject();
            }
        });
        dataPoczatekColumn.setCellValueFactory(new PropertyValueFactory<Leasing, LocalDate>("data_poczatek"));
        dataKoncowaColumn.setCellValueFactory(new PropertyValueFactory<Leasing, LocalDate>("data_koniec"));
        oplataMiesiecznaColumn.setCellValueFactory(new PropertyValueFactory<Leasing, Double>("oplata_miesieczna"));

        leasingObservableList = FXCollections.observableArrayList(leasingService.getLeasingTable());
        leasingTableView.setItems(leasingObservableList);

        //        List<Leasing> list = new ArrayList<>();
        //
        //        session.beginTransaction();
        //        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("LEASING_SEL");
        //        query.execute();
        //
        //        list = query.getResultList();
        //
        //        leasingObservableList = FXCollections.observableArrayList(list);
        //        leasingTableView.setItems(leasingObservableList);
        //        session.getTransaction();
        //        session.close();


        //---- TRANSAKCJE KUPNA INITIALIZE ----|---- TRANSAKCJE KUPNA INITIALIZE ----|
        idTransakcjiColumn.setCellValueFactory(new PropertyValueFactory<Transakcja_kupna, Integer>("id"));
        idKlientaColumn1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transakcja_kupna,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Transakcja_kupna, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getKlient().getId()).asObject();
            }
        });
        idEgzemplarzaColumn1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transakcja_kupna,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Transakcja_kupna, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getEgzemplarz().getId()).asObject();
            }
        });
        dataZakupuColumn.setCellValueFactory(new PropertyValueFactory<Transakcja_kupna, LocalDate>("data_zakupu"));
        dlugoscGwarancjiColumn.setCellValueFactory(new PropertyValueFactory<Transakcja_kupna, Integer>("dlugosc_gwarancji"));

        transakcja_kupnaObservableList = FXCollections.observableArrayList(transakcja_kupnaService.getTransakcja_kupnaTable());
        transakcjeTableView.setItems(transakcja_kupnaObservableList);

        //        List<Transakcje_kupna> list = new ArrayList<>();
        //
        //        session.beginTransaction();
        //        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("TRANSAKCJA_KUPNA_SEL");
        //        query.execute();
        //
        //        list = query.getResultList();
        //
        //        transakcje_kupnaObservableList = FXCollections.observableArrayList(list);
        //        transakcje_kupnaTableView.setItems(transakcje_kupnaObservableList);
        //        session.getTransaction();
        //        session.close();
    }
}