package com.example.shop.controllers;

import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

import static com.example.shop.Configuration.createNewSession;

@CrossOrigin(
        origins = "http://localhost:63325",
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE},
        allowedHeaders = "Content-type"
)
@RestController
@RequestMapping(path = "/shops")
public class ShopController {

    private static SessionFactory factory;
    private static final Properties props = new Properties();

    public void setProps() {
        props.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.setProperty("hibernate.connection.url", "jdbc:postgresql://stampy.db.elephantsql.com:5432/nykbjfyu");
        props.setProperty("hibernate.connection.username", "nykbjfyu");
        props.setProperty("hibernate.connection.password", "BJ0QQMsy2UK45xWFY1PP9GNxa3yNr2jh");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.connection.autocommit", "true");
    }

    public void createFactory() {
        setProps();
        final Configuration config = new Configuration()
                .addAnnotatedClass(com.example.shop.models.ShopPojo.class)
                .addProperties(props);

        factory = config.buildSessionFactory();
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> addShop(@RequestBody ShopDto dto) {
        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ShopPojo pojo = new ShopPojo();
        pojo.setShopName(object.get("shopName").toString());
        pojo.setShopPublic(Boolean.parseBoolean(object.get("shopPublic").toString()));

        session.persist(pojo);
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(200).headers(responseHeaders).build();
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<ShopPojo>> getShops() {
        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops", ShopPojo.class).list();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }

    @GetMapping("/{shopId}")
    public @ResponseBody ResponseEntity<ShopPojo> getShop(@PathVariable long shopId) {
        if (factory == null) {
            createFactory();
        }
        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops where shop_id = " + shopId,
                ShopPojo.class).uniqueResult();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }


    @DeleteMapping("/delete/{shopId}")
    public @ResponseBody ResponseEntity<String> deleteShop(@PathVariable Long shopId) {

        ShopPojo rm_shop = new ShopPojo();
        rm_shop.setShopId(shopId);

        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();


        session.createNativeQuery("delete from shops where shop_id = :id", ShopPojo.class)
                .setParameter("id", rm_shop.getShopId())
                .executeUpdate();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity
                .status(204)
                .headers(responseHeaders)
                .build();
    }
}
