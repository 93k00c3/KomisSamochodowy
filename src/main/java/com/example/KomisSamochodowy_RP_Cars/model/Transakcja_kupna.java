package com.example.KomisSamochodowy_RP_Cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "Transakcja_kupna_Tabelka")
@Table(name="TRANSAKCJA_KUPNA_TABELKA")
@NamedStoredProcedureQuery(
        name = "TRANSAKCJA_KUPNA_SEL",
        procedureName = "TRANSAKCJA_KUPNA_SEL",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Transakcja_kupna.class, name = "my_cursor") })
public class Transakcja_kupna implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klient_id")
    private Klient klient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egzemplarz_id")
    private Egzemplarz egzemplarz;

    @Column(name="data_zakupu")
    private LocalDate data_zakupu;

    @Column(name="dlugosc_gwarancji")
    private Integer dlugosc_gwarancji;

    public Transakcja_kupna(Klient klient,
                            Egzemplarz egzemplarz,
                            LocalDate data_zakupu,
                            int dlugosc_gwarancji) {
        this.klient = klient;
        this.egzemplarz = egzemplarz;
        this.data_zakupu = data_zakupu;
        this.dlugosc_gwarancji = dlugosc_gwarancji;
    }

    public Transakcja_kupna() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Egzemplarz getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(Egzemplarz egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    public LocalDate getData_zakupu() {
        return data_zakupu;
    }

    public void setData_zakupu(LocalDate data_zakupu) {
        this.data_zakupu = data_zakupu;
    }

    public Integer getDlugosc_gwarancji() {
        return dlugosc_gwarancji;
    }

    public void setDlugosc_gwarancji(Integer dlugosc_gwarancji) {
        this.dlugosc_gwarancji = dlugosc_gwarancji;
    }
}