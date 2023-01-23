package com.example.KomisSamochodowy_RP_Cars.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Model_Tabelka")
@Table(name="MODEL_TABELKA")
@NamedStoredProcedureQuery(
        name = "MODEL_SEL",
        procedureName = "MODEL_SEL",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Model.class, name = "my_cursor") })

public class Model implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "nazwa_modelu")
    private String nazwa_modelu;

    @Column(name = "typ_nadwozia")
    private String typ_nadwozia;

    public Model() {
    }

    public Model(String name, String nazwa_modelu,
                 String typ_nadwozia) {
        this.marka = name;
        this.nazwa_modelu = nazwa_modelu;
        this.typ_nadwozia = typ_nadwozia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getNazwa_modelu() {
        return nazwa_modelu;
    }

    public void setNazwa_modelu(String nazwa_modelu) {
        this.nazwa_modelu = nazwa_modelu;
    }

    public String getTyp_nadwozia() {
        return typ_nadwozia;
    }

    public void setTyp_nadwozia(String typ_nadwozia) {
        this.typ_nadwozia = typ_nadwozia;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", nazwa_modelu='" + nazwa_modelu + '\'' +
                ", typ_nadwozia='" + typ_nadwozia + '\'' +
                '}';
    }
}