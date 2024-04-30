package main.java.Cheeseria;

public class Cheese {
    private String id;
    private String name;
    private double pricePerKilo;
    private String color;

    public Cheese(String id, String name, double pricePerKilo, String color) {
        this.id = id;
        this.name = name;
        this.pricePerKilo = pricePerKilo;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPricePerKilo() {
        return pricePerKilo;
    }

    public String getColor() {
        return color;
    }
}