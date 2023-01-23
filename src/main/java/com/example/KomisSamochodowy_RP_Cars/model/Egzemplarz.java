package com.example.KomisSamochodowy_RP_Cars.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Egzemplarz_Tabelka")
@Table(name="EGZEMPLARZ_TABELKA")
@NamedStoredProcedureQuery(
        name = "EGZEMPLARZ_SEL",
        procedureName = "EGZEMPLARZ_SEL",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Egzemplarz.class, name = "my_cursor") })
public class Egzemplarz implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name="rok_produkcji")
    private Integer rok_produkcji;

    @Column(name="pojemnosc_silnika")
    private Integer pojemnosc_silnika;

    @Column(name="przebieg")
    private Integer przebieg;

    @Column(name="rodzaj_paliwa", length = 15)
    private String rodzaj_paliwa;

    @Column(name="kolor", length = 15)
    private String kolor;

    @Column(name="vin", length = 17)
    private String vin;

    @Column(name="dostepnosc")
    private Boolean dostepnosc;

    @Column(name="cena")
    private Integer cena;

    public Egzemplarz(Model model, int rok_produkcji,
                      int pojemnosc_silnika, int przebieg,
                      String rodzaj_paliwa, String kolor,
                      String vin, boolean dostepnosc, int cena) {
        this.model = model;
        this.rok_produkcji = rok_produkcji;
        this.pojemnosc_silnika = pojemnosc_silnika;
        this.przebieg = przebieg;
        this.rodzaj_paliwa = rodzaj_paliwa;
        this.kolor = kolor;
        this.vin = vin;
        this.dostepnosc = dostepnosc;
        this.cena = cena;
    }

    public Egzemplarz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getRok_produkcji() {
        return rok_produkcji;
    }

    public void setRok_produkcji(Integer rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public Integer getPojemnosc_silnika() {
        return pojemnosc_silnika;
    }

    public void setPojemnosc_silnika(Integer pojemnosc_silnika) {
        this.pojemnosc_silnika = pojemnosc_silnika;
    }

    public Integer getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(Integer przebieg) {
        this.przebieg = przebieg;
    }

    public String getRodzaj_paliwa() {
        return rodzaj_paliwa;
    }

    public void setRodzaj_paliwa(String rodzaj_paliwa) {
        this.rodzaj_paliwa = rodzaj_paliwa;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(Boolean dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Egzemplarz{" +
                "id=" + id +
                ", model=" + model +
                ", rok_produkcji=" + rok_produkcji +
                ", pojemnosc_silnika=" + pojemnosc_silnika +
                ", przebieg=" + przebieg +
                ", rodzaj_paliwa='" + rodzaj_paliwa + '\'' +
                ", kolor='" + kolor + '\'' +
                ", vin='" + vin + '\'' +
                ", dostepnosc=" + dostepnosc +
                ", cena=" + cena +
                '}';
    }
}