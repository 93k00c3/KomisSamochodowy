package com.example.KomisSamochodowy_RP_Cars.service;

import com.example.KomisSamochodowy_RP_Cars.HibernateUtil.SingletonConnection;
import com.example.KomisSamochodowy_RP_Cars.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ModelService {

    public static final SessionFactory sessionFactory = SingletonConnection.getSessionFactory();
    public static final Session session = sessionFactory.openSession();

    public void saveModel(Model model) {
        session.beginTransaction();
        Query query = session.createNativeQuery("CALL MODEL_INS(:TYP_NADWOZIA" +
                        ",:NAZWA_MODELU" +
                        ",NULL" +
                        ",:MARKA)")
                .addEntity(Model.class)
                .setParameter("TYP_NADWOZIA", model.getTyp_nadwozia())
                .setParameter("NAZWA_MODELU", model.getNazwa_modelu())
                .setParameter("MARKA", model.getMarka());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void removeModel(Model model) {
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL MODEL_DEL(:id)")
                .addEntity(Model.class)
                .setParameter("id", model.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void updateModel(Model model) {
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL MODEL_UPD(:ID" +
                        ",:MARKA" +
                        ",:NAZWA_MODELU" +
                        ",:TYP_NADWOZIA)")
                .addEntity(Model.class)
                .setParameter("ID", model.getId())
                .setParameter("MARKA", model.getMarka())
                .setParameter("NAZWA_MODELU", model.getNazwa_modelu())
                .setParameter("TYP_NADWOZIA", model.getTyp_nadwozia());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public List<Model> getModelTable() {
        session.beginTransaction();

        Query query = session.createQuery("from Model_Tabelka");

        session.getTransaction().commit();

        return query.list();
    }

    public Model getModelById(int id) {
        session.beginTransaction();
        Model model = (Model) session.get(Model.class, id);
        session.getTransaction().commit();
        return model;
    }

//    public static void main(String[] args) {
//        session.beginTransaction();
//        StoredProcedureQuery query = session.createNamedStoredProcedureQuery("MODEL_SEL");
//        query.execute();
//
////        List<Model> list = query.getResultList();
////        ObservableList<String> kategorie = FXCollections.observableArrayList(list);
////        kategoriaCombo.setItems(kategorie);
//
//    }
}