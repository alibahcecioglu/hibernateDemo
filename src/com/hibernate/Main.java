package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Main {
    public static void main(String[] args){

        SessionFactory factory = new Configuration().configure("/com/hibernate/hibernate.cfg.xml")
                .addAnnotatedClass(City.class).buildSessionFactory();
        Session session =factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<String> countryCodes= session.createQuery("select c.countryCode from City c GROUP BY c.countryCode").getResultList();

            for (String countryCode:countryCodes) {
                System.out.println(countryCode);
            }

            session.getTransaction();
        }finally {
            factory.close();
        }


    }
}
