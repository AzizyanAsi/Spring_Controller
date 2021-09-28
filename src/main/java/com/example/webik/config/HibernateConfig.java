package com.example.webik.config;

import com.example.webik.models.Basket;
import com.example.webik.models.Group;
import com.example.webik.models.Item;
import com.example.webik.models.ItemDetails;
import com.example.webik.util.DatabaseConfigurationUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;
import java.util.Properties;

@Configuration
public class HibernateConfig {
    @Bean
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
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(ItemDetails.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(Basket.class);
    }
}
