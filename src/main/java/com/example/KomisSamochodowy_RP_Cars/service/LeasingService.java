package com.example.KomisSamochodowy_RP_Cars.service;

import com.example.KomisSamochodowy_RP_Cars.HibernateUtil.SingletonConnection;
import com.example.KomisSamochodowy_RP_Cars.model.Leasing;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class LeasingService {
    public static final SessionFactory sessionFactory = SingletonConnection.getSessionFactory();
    public static final Session session = sessionFactory.openSession();

    public void saveLeasing(Leasing leasing){
        session.beginTransaction();
        Date data_poczatek = Date.valueOf(leasing.getData_poczatek());
        Date data_koniec = Date.valueOf(leasing.getData_koniec());
        Query query = session.createNativeQuery("CALL LEASING_INS(:EGZEMPLARZ_ID" +
                        ",:EGZEMPLARZ" +
                        ",:OPLATA_MIESIECZNA" +
                        ",:DATA_POCZĄTEK" +
                        ",:KLIENT_ID" +
                        ",NULL" +
                        ",:DATA_KONIEC" +
                        ",:KLIENT)")
                .addEntity(Leasing.class)
                .setParameter("DATA_POCZĄTEK", data_poczatek)
                .setParameter("DATA_KONIEC", data_koniec)
                .setParameter("EGZEMPLARZ_ID", leasing.getEgzemplarz().getId())
                .setParameter("KLIENT_ID", leasing.getKlient().getId())
                .setParameter("EGZEMPLARZ", leasing.getEgzemplarz())
                .setParameter("OPLATA_MIESIECZNA", leasing.getOplata_miesieczna())
                .setParameter("KLIENT", leasing.getKlient());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void removeLeasing(Leasing leasing){
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL LEASING_DEL(:id)")
                .addEntity(Leasing.class)
                .setParameter("id", leasing.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void updateLeasing(Leasing leasing) {
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL LEASING_UPD(:ID" +
                        ",:KLIENT_ID" +
                        ",:EGZEMPLARZ_ID" +
                        ",:DATA_POCZĄTEK" +
                        ",:DATA_KONIEC" +
                        ",:OPLATA_MIESIECZNA)")
                .addEntity(Leasing.class)
                .setParameter("ID", leasing.getId())
                .setParameter("KLIENT_ID", leasing.getKlient().getId())
                .setParameter("EGZEMPLARZ_ID", leasing.getEgzemplarz().getId())
                .setParameter("DATA_POCZĄTEK", leasing.getData_poczatek())
                .setParameter("DATA_KONIEC", leasing.getData_koniec())
                .setParameter("OPLATA_MIESIECZNA", leasing.getOplata_miesieczna());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public List<Leasing> getLeasingTable(){
        session.beginTransaction();

        Query query = session.createQuery("from Leasing_Tabelka");

        session.getTransaction().commit();

        return query.list();
    }

    public Leasing getLeasingById(int id) {
        session.beginTransaction();
        Leasing leasing = (Leasing) session.get(Leasing.class, id);
        session.getTransaction().commit();
        return leasing;
    }

    public static void main(String[] args) {
        EgzemplarzService egzemplarzService = new EgzemplarzService();
        KlientService klientService = new KlientService();
        Leasing leasing = new Leasing(klientService.getKlientById(53), egzemplarzService.getEgzemplarzById(49), null, null, 12);
        LeasingService leasingService = new LeasingService();
        leasingService.saveLeasing(leasing);
        System.out.println("DODANO LEASING?");
        leasingService.updateLeasing(leasing);
        System.out.println("zmieniono LEASING?");

    }

}