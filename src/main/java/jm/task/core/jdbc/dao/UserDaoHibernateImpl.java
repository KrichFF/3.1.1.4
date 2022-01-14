package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(
                            "CREATE TABLE if not exists User " +
                                    "  (id INT NOT NULL auto_increment," +
                                    "  name VARCHAR(45) NULL," +
                                    "  lastName VARCHAR(45) NULL," +
                                    "  age INT NOT NULL," +
                                    "  PRIMARY KEY (id))")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from User where id = :id").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            list = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("truncate table User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
