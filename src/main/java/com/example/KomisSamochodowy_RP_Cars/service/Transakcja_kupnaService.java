package com.example.KomisSamochodowy_RP_Cars.service;

import com.example.KomisSamochodowy_RP_Cars.HibernateUtil.SingletonConnection;
import com.example.KomisSamochodowy_RP_Cars.model.Transakcja_kupna;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class Transakcja_kupnaService {
    public static final SessionFactory sessionFactory = SingletonConnection.getSessionFactory();
    public static final Session session = sessionFactory.openSession();

    public void saveTransakcja_kupna(Transakcja_kupna transakcja_kupna){
        session.beginTransaction();
        Date data = Date.valueOf(transakcja_kupna.getData_zakupu());
        Query query = session.createNativeQuery("CALL TRANSAKCJA_KUPNA_INS(:EGZEMPLARZ_ID" +
                        ",:DLUGOSC_GWARANCJI" +
                        ",:DATA_ZAKUPU" +
                        ",:KLIENT_ID" +
                        ",NULL)")
                .addEntity(Transakcja_kupna.class)
                .setParameter("DATA_ZAKUPU", data)
                .setParameter("DLUGOSC_GWARANCJI", transakcja_kupna.getDlugosc_gwarancji())
                .setParameter("EGZEMPLARZ_ID", transakcja_kupna.getEgzemplarz().getId())
                .setParameter("KLIENT_ID", transakcja_kupna.getKlient().getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void removeTransakcja_kupna(Transakcja_kupna transakcja_kupna){
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL TRANSAKCJA_KUPNA_DEL(:ID)")
                .addEntity(Transakcja_kupna.class)
                .setParameter("ID", transakcja_kupna.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void updateTransakcja_kupna(Transakcja_kupna transakcja_kupna) {
        session.beginTransaction();
        Query query = session.createNativeQuery("CALL TRANSAKCJA_KUPNA_UPD(:ID" +
                        ",:KLIENT_ID" +
                        ",:EGZEMPLARZ_ID" +
                        ",:DATA_ZAKUPU" +
                        ",:DLUGOSC_GWARANCJI)")
                .addEntity(Transakcja_kupna.class)
                .setParameter("ID", transakcja_kupna.getId())
                .setParameter("KLIENT_ID", transakcja_kupna.getKlient().getId())
                .setParameter("EGZEMPLARZ_ID", transakcja_kupna.getEgzemplarz().getId())
                .setParameter("DATA_ZAKUPU", transakcja_kupna.getData_zakupu())
                .setParameter("DLUGOSC_GWARANCJI", transakcja_kupna.getDlugosc_gwarancji());
                query.executeUpdate();
        session.getTransaction().commit();
    }

    public List<Transakcja_kupna> getTransakcja_kupnaTable(){
        session.beginTransaction();

        Query query = session.createQuery("from Transakcja_kupna_Tabelka");

        session.getTransaction().commit();

        return query.list();
    }
    public Transakcja_kupna getTransakcja_kupnaById(int id) {
        session.beginTransaction();
        Transakcja_kupna transakcja_kupna = (Transakcja_kupna) session.get(Transakcja_kupna.class, id);
        session.getTransaction().commit();
        return transakcja_kupna;
    }
}