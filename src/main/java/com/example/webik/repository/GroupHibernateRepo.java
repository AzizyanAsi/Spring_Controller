package com.example.webik.repository;

import com.example.webik.models.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class GroupHibernateRepo {
    private final SessionFactory sessionFactory;

    public GroupHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Group> getGroup(long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Group> query = session.createQuery(
                "select g from Group g" +
                        " where g.id = :id ",
                Group.class);
        query.setParameter("id", groupId);
        Group group = query.getSingleResult();

        transaction.commit();
        session.close();

        return Optional.ofNullable(group);
    }

    public List<? extends Group> findGroup(String pName) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Group> query = session.createQuery(
                "select g from Group g" +
                        " where g.name = :name ",
                Group.class);
        query.setParameter("name", pName);
        List<? extends Group> groups = query.getResultList();


        transaction.commit();
        session.close();

        return groups;
    }

    public List<? extends Group> getAllGroups() {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        String q = "from Group g";
        Query<Group> query = session.createQuery(q, Group.class);
        List<? extends Group> groups = query.getResultList();

        transaction.commit();
        session.close();

        return groups;
    }

    public Group update(Group group) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Group existing = session.get(Group.class, group.getId());
        existing.setName(group.getName());
        existing.setGroupParent(group.getGroupParent());

        transaction.commit();

        session.close();

        return existing;
    }

    public boolean deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String q = "delete from Group g" +
                " where g.id = :id";

        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }

    public boolean delete(Group group) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(group);

        session.close();

        return true;
    }
    public Group insert(Group group) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(group);

        transaction.commit();

        session.close();

        return group;
    }

}
