package com.example.webik.config;

import com.example.webik.models.*;
import com.example.webik.util.DatabaseConfigurationUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class HibernateConfig {

    public SessionFactory sessionFactory(){
        return configure().buildSessionFactory();
    }
    public org.hibernate.cfg.Configuration configure() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addProperties(hibernateProperties());
        addAnnotatedClasses(configuration);

        return configuration;
    }

    public Properties hibernateProperties() {
        return DatabaseConfigurationUtil
                .readProperties("hibernate.properties");
    }

    private void addAnnotatedClasses(org.hibernate.cfg.Configuration configuration) {
//        configuration.addAnnotatedClass(Item.class);
//        configuration.addAnnotatedClass(ItemDetails.class);
//        configuration.addAnnotatedClass(Group.class);
//        configuration.addAnnotatedClass(Basket.class);
        configuration.addAnnotatedClass(Configuration.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(ItemDetails.class);
        configuration.addAnnotatedClass(Basket.class);
        configuration.addAnnotatedClass(ItemBasket.class);
        configuration.addAnnotatedClass(Generative.class);
        configuration.addAnnotatedClass(Stock.class);
        configuration.addAnnotatedClass(Group.class);
    }
}
