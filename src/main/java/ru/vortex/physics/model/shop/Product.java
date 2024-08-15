package ru.vortex.physics.model.shop;

public class Product {
    private String name;
    private float price;
    private String description;
    private float sale;

    public Product(String name, float price, String description, float sale) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sale = sale;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }
}
