package com.shop.undercromo.models;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private String name;
    private String price;
    private int imageRes;
    private String description;
    private int stock;

    public Product(String id, String name, String price, int imageRes, String description, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
        this.description = description;
        this.stock = stock;
    }

    public Product() {}

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageRes() { return imageRes; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(String price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setStock(int stock) { this.stock = stock; }
}
