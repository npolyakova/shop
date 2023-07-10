package com.example.shop.controllers;

import com.example.shop.Configuration;
import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.shop.ShopHandler.checkFirstLetter;
import static com.example.shop.ShopHandler.checkLength;


@RestController
@RequestMapping(path = "/shop")
public class ShopController extends Configuration {

    SessionFactory factory = buildFactory();

    @PostMapping("/add")
    public String createShop(@RequestBody ShopDto dto) {
        Session session = createNewSession(factory);
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ShopPojo pojo = new ShopPojo();
        pojo.setShopName(object.get("shopName").toString());
        pojo.setShopPublic(Boolean.parseBoolean(object.get("shopPublic").toString()));

        if(!checkLength(pojo.getShopName(), 7)) {
            return "400. Enter the name with length of 7 or more symbols";
        } else if (!checkFirstLetter(pojo.getShopName())) {
            return "400. The first letter should be capital";
        }

        session.persist(pojo);
        transaction.commit();
        session.close();

        return "200";
    }

    @GetMapping("/{shopId}")
    public ShopPojo getShop(@PathVariable long shopId) {
        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops where shop_id = " + shopId,
                ShopPojo.class).uniqueResult();
        transaction.commit();

        session.close();
        return res;
    }


    @GetMapping("/all")
    public List<ShopPojo> getAllShops() {
        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops", ShopPojo.class).list();
        transaction.commit();

        session.close();
        return res;
    }

    @DeleteMapping("/delete/{shopId}")
    public String deleteProduct(@PathVariable long shopId) {
        ShopPojo p = new ShopPojo();
        p.setShopId(shopId);

        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from shops where shop_id = :id", ShopPojo.class)
                .setParameter("id", p.getShopId())
                .executeUpdate();
        transaction.commit();

        session.close();
        return "204";
    }

}
