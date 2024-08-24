package ru.vortex.leafcity.jwt_manager;

public class RefreshJwtRequest {

    public String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
