package main.java;

import java.util.UUID;

public class Cheese {
    private String id;
    private String name;
    private double pricePerKilo;
    private String color;
    private String imageUrl;

    public Cheese(String id, String name, double pricePerKilo, String color, String imageUrl) {
        this.id = (id != null) ? id : UUID.randomUUID().toString();
        this.name = name;
        this.pricePerKilo = pricePerKilo;
        this.color = color;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(double pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}