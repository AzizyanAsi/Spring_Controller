package com.example.webik.repository;


import com.example.webik.models.Group;
import com.example.webik.models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ItemHibernateRepo {

    private SessionFactory sessionFactory;

    public ItemHibernateRepo( ) {
        this.sessionFactory =HibernateSessionFactoryUtil.getSessionfactory();
    }

    public void attachItemToGroup(String itemId, String groupId) {
        Session session = HibernateSessionFactoryUtil.getSessionfactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, itemId);
        Group group = session.get(Group.class, groupId);

        item.setName("newItem");

        group.addItem(item);

        transaction.commit();
        session.close();
    }

    public Optional<Item> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);

        transaction.commit();
        session.close();

        return Optional.ofNullable(item);
    }


    public Item insert(Item item) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        if (item.getItemDetail() != null) {
            item.getItemDetail().setItem(item);
        }
        session.save(item);

        transaction.commit();

        session.close();

        return item;
    }

    public List<? extends Item> getAllItems() {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        String q = "from Item i";
        Query<Item> query = session.createQuery(q, Item.class);
        List<? extends Item> items = query.getResultList();

        transaction.commit();
        session.close();

        return items;
    }

    public Item update(Item item) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Item existing = session.get(Item.class, item.getId());
        existing.setName(item.getName());
        existing.setPrice(item.getPrice());

        transaction.commit();

        session.close();

        return existing;
    }

    public boolean updateById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String q = "update item set item.name=:name " +
                " where item.id = :id";

        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }

    public boolean delete(Item item) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(item);

        session.close();

        return true;
    }

    public boolean deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String q = "delete from Item i" +
                " where i.id = :id";

        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }

    public List<Item> findItems(Predicate<Item> searchPredicate) {
        return new ArrayList<>();
    }


}
