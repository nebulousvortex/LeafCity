package ru.vortex.physics.model.payment;

public class Webhook {
    private String type;
    private String event;
    private Payment payment;

    public Webhook(String type, String event, Payment payment) {
        this.type = type;
        this.event = event;
        this.payment = payment;
    }

    public Webhook() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
