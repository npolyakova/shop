package com.example.shop.models;

public class ProductDto {

    private Long id;

    private String name;

    private Integer count;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public ProductDto(Long id, String name, Integer count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
