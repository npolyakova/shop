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

@CrossOrigin(
        origins = "http://localhost:63325",
        methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE },
        allowedHeaders = "Content-type"
)
@RestController
@RequestMapping(path = "/product")
public class ProductController {

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
                .addAnnotatedClass(com.example.shop.models.ProductPojo.class)
                .addProperties(props);

        factory = config.buildSessionFactory();
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> createProduct(@RequestBody ProductDto dto) {
        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ProductPojo pojo = new ProductPojo();
        pojo.setName(object.get("name").toString());
        pojo.setCount(Integer.valueOf(object.get("count").toString()));

        session.persist(pojo);
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(204).headers(responseHeaders).build();
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<ProductPojo>> getProduct() {
        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from product", ProductPojo.class).list();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.set("Access-Control-Allow-Origin", "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        if (factory == null) {
            createFactory();
        }
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from product where id=" + productId, ProductPojo.class).executeUpdate();

        transaction.commit();
        session.close();
    }
}
