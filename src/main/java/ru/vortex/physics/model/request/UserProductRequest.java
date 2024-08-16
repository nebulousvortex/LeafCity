package ru.vortex.physics.model.request;

public class UserProductRequest {
    private String username;
    private Long productId;

    public UserProductRequest(String username, Long productId) {
        this.username = username;
        this.productId = productId;
    }

    public UserProductRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
