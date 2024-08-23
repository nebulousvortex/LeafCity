package ru.vortex.physics.model.request;

public class UserProductRequest {
    private String username;
    private Long productId;
    private String redirectUrl;

    public UserProductRequest(String username, Long productId, String redirectUrl) {
        this.username = username;
        this.productId = productId;
        this.redirectUrl = redirectUrl;
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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
