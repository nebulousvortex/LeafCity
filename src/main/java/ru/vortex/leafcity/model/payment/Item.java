package ru.vortex.leafcity.model.payment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Item{
    String description;
    @Embedded
    Amount amount;
    Integer vat_code;
    Number quantity;
    String measure;

    public Item(String description, Amount amount, Integer vat_code, Number quantity, String measure) {
        this.description = description;
        this.amount = amount;
        this.vat_code = vat_code;
        this.quantity = quantity;
        this.measure = measure;
    }

    public Item() {
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Integer getVat_code() {
        return vat_code;
    }

    public void setVat_code(Integer vat_code) {
        this.vat_code = vat_code;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }
}