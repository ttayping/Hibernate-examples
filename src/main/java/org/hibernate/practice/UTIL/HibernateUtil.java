package org.hibernate.practice.UTIL;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.practice.MODEL.Employer;

import javax.security.auth.login.AppConfigurationEntry;

public class HibernateUtil {
    private HibernateUtil() {
    }

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/company");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "1312acab");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.addAnnotatedClass(Employer.class); // Add your entity classes here
            // Build the SessionFactory
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            return configuration.buildSessionFactory(serviceRegistryBuilder.build());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory: " + exception.getMessage(), exception);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
}
