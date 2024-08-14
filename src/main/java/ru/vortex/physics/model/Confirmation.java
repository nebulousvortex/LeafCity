package ru.vortex.physics.model;

public class Confirmation {
    private String type;
    private String confirmation_token;

    public Confirmation(String type) {
        this.type = type;
    }

    public Confirmation() {
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
