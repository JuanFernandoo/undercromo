package com.shop.undercromo.data;

import com.shop.undercromo.R;
import com.shop.undercromo.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static ProductRepository instance;
    private List<Product> products;

    private ProductRepository() {
        products = new ArrayList<>();
        loadSampleProducts();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private void loadSampleProducts() {
        products.add(new Product("1", "The Path Birds T-shirt Black", "$100", R.drawable.product_placeholder, "Camisa negra edición especial", 15));
        products.add(new Product("2", "The Path Gold Hoodie Rose Fire", "$250", R.drawable.product2, "Hoodie premium color Rose Fire", 10));
        products.add(new Product("3", "Hekios Dazzled Boxy Fit T-shirt Faded Black", "$150", R.drawable.product3, "Camiseta Boxy Fit tono Faded Black", 8));
        products.add(new Product("4", "Thunder Rider Pegasus Zip Up Jacket Rusty Black", "$300", R.drawable.product4, "Chaqueta zip-up edición Pegasus", 5));
        products.add(new Product("5", "Gray T-Shirt", "$80", R.drawable.product5, "Camiseta gris básica premium", 20));
        products.add(new Product("6", "UnderGold Sweatshirt", "$120", R.drawable.product6, "Sudadera UnderGold edición limitada", 12));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void updateProduct(int position, Product product) {
        products.set(position, product);
    }

    public void removeProduct(int position) {
        products.remove(position);
    }
}
