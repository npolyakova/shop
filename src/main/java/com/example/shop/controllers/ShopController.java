package com.example.shop.controllers;

import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import jakarta.persistence.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;


@RestController
@Entity
@RequestMapping(path = "/shop")
public class ShopController {

    private static Session session;
    private static Properties props = new Properties();

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
                    //.addResource("cfg.xml")
                    .addAnnotatedClass(com.example.shop.models.ShopPojo.class)
                    .addProperties(props);
            SessionFactory factory = config.buildSessionFactory();

            session = factory.openSession();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public String createShop(@RequestBody ShopDto dto) {
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ShopPojo pojo = new ShopPojo();
        pojo.setShopName(object.get("shopName").toString());
        pojo.setShopPublic(Boolean.parseBoolean(object.get("shopPublic").toString()));

        session.persist(pojo);
        transaction.commit();

        return "200";
    }

    @GetMapping("/{shopId}")
    public ShopPojo getShop(@PathVariable long shopId) {
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops where shop_id = " + shopId,
                ShopPojo.class).uniqueResult();
        transaction.commit();

        return res;
    }


    @GetMapping("/all")
    public List<ShopPojo> getAllShops() {
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops", ShopPojo.class).list();
        transaction.commit();

        return res;
    }

    @DeleteMapping("/delete/{shopId}")
    public String deleteProduct(@PathVariable long shopId) {
        ShopPojo p = new ShopPojo();
        p.setShopId(shopId);



        Transaction transaction = session.beginTransaction();

//        session.remove(p);//.createNativeQuery("delete from shops where shop_id = " + shopId, ShopPojo.class);
//        transaction.commit();
        session.createNativeQuery("delete from shops where shop_id = :id")
                .setParameter("id", p.getShopId())
                .executeUpdate();
        transaction.commit();

        return "204";
    }

}
