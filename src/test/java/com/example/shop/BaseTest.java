package com.example.shop;

import com.example.shop.models.ProductPojo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class BaseTest {

    private static Session session;
    private static Properties props = new Properties();

    static {
        try {
            props.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            props.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/shop");
            props.setProperty("hibernate.connection.username", "nataliya");
            props.setProperty("hibernate.connection.password", "qwert");
            props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            props.setProperty("hibernate.show_sql", "true");
            props.setProperty("hibernate.hbm2ddl.auto", "update");

            final Configuration config = new Configuration()
                    //.addResource("cfg.xml")
                    .addAnnotatedClass(com.example.shop.models.ProductPojo.class)
                    .addProperties(props);
            SessionFactory factory = config.buildSessionFactory();

            session = factory.openSession();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProductPojo generateSomeProducts() {
        Transaction tr = session.beginTransaction();
        final ProductPojo newProduct = new ProductPojo();
        newProduct.setCount(3);
        newProduct.setName("name");
        session.persist(newProduct);
        tr.commit();

        return newProduct;
    }
}
