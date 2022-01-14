package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class Util {

    public static Connection getMySQLConnection() {
        String connectionURL = "jdbc:mysql://localhost:3306/pp";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionURL, "root", "1234");
        } catch (SQLException e) {
            System.out.println("Открылось");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pp?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "1234");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "false");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();


        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не закрылось");
        }
    }

    public static void disconnect(Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (HeadlessException ignore) {
        }
    }


}
