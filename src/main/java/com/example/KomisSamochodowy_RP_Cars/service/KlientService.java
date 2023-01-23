package com.example.KomisSamochodowy_RP_Cars.service;

import com.example.KomisSamochodowy_RP_Cars.HibernateUtil.SingletonConnection;
import com.example.KomisSamochodowy_RP_Cars.model.Klient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class KlientService {
    public static final SessionFactory sessionFactory = SingletonConnection.getSessionFactory();
    public static final Session session = sessionFactory.openSession();

    public void saveKlient(Klient klient){
        session.beginTransaction();
        Date data = Date.valueOf(klient.getData_urodzenia());
        Query query = session.createNativeQuery(   """
                         CALL KLIENT_INS(
                         :ULICA
                        ,:IMIE
                        ,:PESEL
                        ,:TELEFON
                        ,:EMAIL 
                        ,:NAZWISKO 
                        ,:KOD_POCZTOWY 
                        ,:NIP 
                        ,:NUMER_DOWODU
                        ,:NUMER_DOMU 
                        ,:MIEJSCOWOSC 
                        ,:DATA_URODZENIA
                        ,NULL 
                        ,:FIRMA)       
                """)
                .addEntity(Klient.class)
                .setParameter("DATA_URODZENIA", data)
                .setParameter("EMAIL", klient.getEmail())
                .setParameter("FIRMA", klient.getFirma())
                .setParameter("IMIE", klient.getImie())
                .setParameter("KOD_POCZTOWY", klient.getKod_pocztowy())
                .setParameter("MIEJSCOWOSC", klient.getMiejscowosc())
                .setParameter("NAZWISKO", klient.getNazwisko())
                .setParameter("NIP", klient.getNip())
                .setParameter("NUMER_DOMU", klient.getNumer_domu())
                .setParameter("NUMER_DOWODU", klient.getNumer_dowodu())
                .setParameter("TELEFON", klient.getTelefon())
                .setParameter("PESEL", klient.getPesel())
                .setParameter("ULICA", klient.getUlica());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void removeKlient(Klient klient){
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL KLIENT_DEL(:id)")
                .addEntity(Klient.class)
                .setParameter("id", klient.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void updateKlient(Klient klient)
    {
        session.beginTransaction();
        Date data = Date.valueOf(klient.getData_urodzenia());
        Query query = session.createNativeQuery(   """
                         CALL KLIENT_UPD(
                                         NULL
                                         ,:ULICA
                                         ,:TELEFON
                                         ,:PESEL
                                         ,:IMIE
                                         ,:EMAIL
                                         ,:NAZWISKO
                                         ,:KOD_POCZTOWY
                                         ,:NUMER_DOWODU
                                         ,:NIP
                                         ,:NUMER_DOMU
                                         ,:MIEJSCOWOSC
                                         ,:DATA_URODZENIA
                                         ,:FIRMA)
                """)
                .addEntity(Klient.class)
                .setParameter("ULICA", klient.getUlica())
                .setParameter("TELEFON", klient.getTelefon())
                .setParameter("PESEL", klient.getPesel())
                .setParameter("IMIE", klient.getImie())
                .setParameter("EMAIL", klient.getEmail())
                .setParameter("NAZWISKO", klient.getNazwisko())
                .setParameter("KOD_POCZTOWY", klient.getKod_pocztowy())
                .setParameter("NUMER_DOWODU", klient.getNumer_dowodu())
                .setParameter("NIP", klient.getNip())
                .setParameter("NUMER_DOMU", klient.getNumer_domu())
                .setParameter("MIEJSCOWOSC", klient.getMiejscowosc())
                .setParameter("DATA_URODZENIA", data)
                .setParameter("FIRMA", klient.getFirma());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public List<Klient> getKlientTable(){
        session.beginTransaction();

        Query query = session.createQuery("from Klient_Tabelka");

        session.getTransaction().commit();

        return query.list();
    }

    public Klient getKlientById(int id) {
        session.beginTransaction();

        Klient klient = (Klient) session.get(Klient.class, id);

        session.getTransaction().commit();

        return klient;
    }

    public static void main(String[] args) {
        System.out.println(1);

        String data_urodzeniaStr = "2020-10-10";
        LocalDate data_urodzenia = LocalDate.parse(data_urodzeniaStr);

        System.out.println(data_urodzenia);

        System.out.println(2);

        Klient klient = new Klient("a", "a", "a", "a", "a", "a", data_urodzenia, "a", "a", "a", 1, "a", "a" );

        System.out.println(klient);
        System.out.println(3);

        KlientService klientService = new KlientService();

        System.out.println(4);

        klientService.updateKlient(klient);

        System.out.println(5);

    }
}
