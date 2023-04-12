package com.example.shop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class ProductPojo {

    private Long id;

    private String name;

    private Integer count;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Column(name = "count")
    public Integer getCount() {
        return count;
    }
}
