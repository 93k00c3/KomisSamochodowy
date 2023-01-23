package com.example.KomisSamochodowy_RP_Cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity(name = "Klient_Tabelka")
@Table(name="KLIENT_TABELKA")
@NamedStoredProcedureQuery(
        name = "KLIENT_SEL",
        procedureName = "KLIENT_SEL",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Klient.class, name = "my_cursor") })
public class Klient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private int id;

    @Column(name="imie", length = 15)
    private String imie;

    @Column(name="nazwisko", length = 20)
    private String nazwisko;

    @Column(name="data_urodzenia")
    private LocalDate data_urodzenia;

    @Column(name="pesel", length = 11)
    private String pesel;

    @Column(name="numer_dowodu", length = 6)
    private String numer_dowodu;

    @Column(name="email")
    private String email;

    @Column(name="telefon", length = 9)
    private String telefon;

    @Column(name="miejscowosc", length = 25)
    private String miejscowosc;

    @Column(name="kod_pocztowy", length = 6)
    private String kod_pocztowy;

    @Column(name="ulica", length = 25)
    private String ulica;

    @Column(name="numer_domu")
    private Integer numer_domu;

    @Column(name="firma")
    private String firma;

    @Column(name="nip")
    private String nip;

    public Klient(String imie, String nazwisko,
                  String pesel, String email,
                  String numer_dowodu, String telefon,
                  LocalDate data_urodzenia, String miejscowosc,
                  String kod_pocztowy, String ulica,
                  int numer_domu, String firma, String nip) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.email = email;
        this.numer_dowodu = numer_dowodu;
        this.telefon = telefon;
        this.data_urodzenia = data_urodzenia;
        this.miejscowosc = miejscowosc;
        this.kod_pocztowy = kod_pocztowy;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.firma = firma;
        this.nip = nip;
    }

    public Klient(String imie, String nazwisko,
                  LocalDate data_urodzenia, String pesel,
                  String numer_dowodu, String email,
                  String telefon, String miejscowosc,
                  String kod_pocztowy, String ulica, int numer_domu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.pesel = pesel;
        this.numer_dowodu = numer_dowodu;
        this.email = email;
        this.telefon = telefon;
        this.miejscowosc = miejscowosc;
        this.kod_pocztowy = kod_pocztowy;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
    }

    public Klient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(LocalDate data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNumer_dowodu() {
        return numer_dowodu;
    }

    public void setNumer_dowodu(String numer_dowodu) {
        this.numer_dowodu = numer_dowodu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Integer getNumer_domu() {
        return numer_domu;
    }

    public void setNumer_domu(Integer numer_domu) {
        this.numer_domu = numer_domu;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}