package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean replaced = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = sf.openSession();
            tx = session.beginTransaction();
            Item existingItem = session.get(Item.class, id);
            if (existingItem != null) {
                existingItem.setName(item.getName());
                existingItem.setCreated(item.getCreated());
                session.update(existingItem);
                tx.commit();
                replaced = true;
            } else {
                System.out.println("There's no item with id " + id);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return replaced;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = sf.openSession();
            tx = session.beginTransaction();
            session.createQuery("DELETE Item WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }

        }
        return deleted;
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            List<Item> queryList = session.createQuery("from Item", Item.class).list();
            return queryList;
        }
    }

    @Override
    public List<Item> findByName(String key) {
        try (Session session = sf.openSession()) {
            List<Item> queryList = session.createQuery("from Item i WHERE i.name LIKE :key", Item.class)
                    .setParameter("key", "%" + key + "%").list();
            return queryList;
        }
    }

    @Override
    public Item findById(int id) {
        try (Session session = sf.openSession()) {
            Query<Item> query = session.createQuery("from Item i WHERE i.id = :id", Item.class)
                    .setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}