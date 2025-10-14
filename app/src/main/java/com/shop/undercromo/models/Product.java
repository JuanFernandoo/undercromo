package com.shop.undercromo.models;

public class Product {

    private String name; // Nombre del producto
    private String price; // Precio del producto
    private int imageResId; // ID del recurso de la imagen

    // Constructor
    public Product(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getters (métodos para acceder a los datos)
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}