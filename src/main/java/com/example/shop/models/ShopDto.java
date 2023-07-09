package com.example.shop.models;

public class ShopDto {

    private Long shop_id;

    private String shop_name;

    private boolean shop_type;

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_type(boolean shop_type) {
        this.shop_type = shop_type;
    }

    public boolean isShop_type() {
        return shop_type;
    }

    public ShopDto(Long shop_id, String shop_name, boolean shop_type) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_type = shop_type;
    }
}
