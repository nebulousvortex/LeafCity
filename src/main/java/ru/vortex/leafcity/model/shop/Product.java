package ru.vortex.leafcity.model.shop;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    @ElementCollection
    private List<Information> about;
    private float sale;
    private String command;
    private String notify;
    @Transient
    private float realPrice;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "duration_id")
    private Duration duration;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    private List<String> features;
    @ElementCollection
    private List<Information> abilities;

    public Product() {
    }
    @PreRemove
    private void clearProduct(){
        this.category = null;
        this.duration = null;
    }

    public List<Information> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Information> abilities) {
        this.abilities = abilities;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Information> getAbout() {
        return about;
    }

    public void setAbout(List<Information> about) {
        this.about = about;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public float getRealPrice() {
        return this.price - ( this.price * this.sale / 100 );
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
    }
}
