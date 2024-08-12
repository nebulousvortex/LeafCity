package ru.vortex.physics.model;

public class Ball {
    private double x;
    private double y;
    private String sessionId;

    public Ball(double x, double y, String sessionId) {
        this.x = x;
        this.y = y;
        this.sessionId = sessionId;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getSessionId() {
        return sessionId;
    }
}
