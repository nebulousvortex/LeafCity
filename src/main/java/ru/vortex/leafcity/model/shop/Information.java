package ru.vortex.leafcity.model.shop;

import javax.persistence.Embeddable;

@Embeddable
public class Information {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        }
}
