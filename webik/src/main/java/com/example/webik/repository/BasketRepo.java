package com.example.webik.repository;

import com.example.webik.models.Basket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class BasketRepo {
    private final SessionFactory sessionFactory;

    public BasketRepo( ) {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionfactory();
    }
    public Optional<Basket> getBasket(Long basketId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Basket basket = session.get(Basket.class, basketId);

        transaction.commit();
        session.close();

        return Optional.ofNullable(basket);
    }
}
