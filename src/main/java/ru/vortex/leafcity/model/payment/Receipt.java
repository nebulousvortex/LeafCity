package ru.vortex.leafcity.model.payment;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Embeddable
public class Receipt {
    @Embedded
    @ElementCollection
    List<Item> items;

    public Receipt() {
    }

    public Receipt(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}


