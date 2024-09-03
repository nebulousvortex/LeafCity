package ru.vortex.leafcity.model.request;

import ru.vortex.leafcity.model.shop.Product;

public class ProductGiftRequest {
    private String username;
    private Product product;

    public ProductGiftRequest(String username, Product product) {
        this.username = username;
        this.product = product;
    }

    public ProductGiftRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
