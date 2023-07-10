package com.example.shop.controllers;

import com.example.shop.models.ProductDto;
import com.example.shop.models.ProductPojo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private static Session session;
    private static final Properties props = new Properties();

    static {
        try {
            props.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            props.setProperty("hibernate.connection.url", "jdbc:postgresql://stampy.db.elephantsql.com:5432/nykbjfyu");
            props.setProperty("hibernate.connection.username", "nykbjfyu");
            props.setProperty("hibernate.connection.password", "BJ0QQMsy2UK45xWFY1PP9GNxa3yNr2jh");
            props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            props.setProperty("hibernate.show_sql", "true");
            props.setProperty("hibernate.hbm2ddl.auto", "update");
            props.setProperty("hibernate.connection.autocommit", "true");

            final Configuration config = new Configuration()
                    .addAnnotatedClass(com.example.shop.models.ProductPojo.class)
                    .addProperties(props);
            SessionFactory factory = config.buildSessionFactory();

            session = factory.openSession();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public void createProduct(@RequestBody ProductDto dto) {
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ProductPojo pojo = new ProductPojo();
        pojo.setName(object.get("name").toString());
        pojo.setCount(Integer.valueOf(object.get("count").toString()));

        session.persist(pojo);
        transaction.commit();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Headers", "*");

        ResponseEntity.ok().headers(responseHeaders);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductPojo>> getProduct() {
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from product", ProductPojo.class).list();
        transaction.commit();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }

    @DeleteMapping("/delete")
    public void deleteProduct() {
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from product where id<1000", ProductPojo.class).list();
        transaction.commit();
    }
}
