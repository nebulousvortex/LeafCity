package ru.vortex.physics.model.payment;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentMethodData {
    private String type;

    public PaymentMethodData(String type) {
        this.type = type;
    }

    public PaymentMethodData() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
