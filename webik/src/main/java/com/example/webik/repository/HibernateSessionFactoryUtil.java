package com.example.webik.repository;

import com.example.webik.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateSessionFactoryUtil {

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionfactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Configuration.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(ItemDetails.class);
        configuration.addAnnotatedClass(Basket.class);
        configuration.addAnnotatedClass(ItemBasket.class);
        configuration.addAnnotatedClass(Generative.class);
        configuration.addAnnotatedClass(Stock.class);
        configuration.addAnnotatedClass(Group.class);
        return configuration.buildSessionFactory();
    }

}