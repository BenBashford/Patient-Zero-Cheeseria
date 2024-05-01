package main.java;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// Service class for managing Cheese entities
// Currently no persistence mechanism included, out of scope for a POC mockup.
// If developed further, something like Spring Data JPA connected to a MySQL or PostgreSQL database would be appropriate for storing information outside of session.
// CheeseeriaController would be modified to fetch from database, CheeseService would post, put, and delete to and from database.

@Service
public class CheeseService {

    // List to store cheese entities
    private List<Cheese> cheeses = new ArrayList<>();

    // Default constructor
    public CheeseService() {
    }

    // Method to retrieve all cheeses
    public List<Cheese> getAllCheeses() {
        return cheeses;
    }

    // Method to retrieve a cheese by its ID
    public Cheese getCheeseById(String id) {
        for (Cheese cheese : cheeses) {
            if (cheese.getId().equals(id)) {
                return cheese;
            }
        }
        return null;
    }

    // Method to create a new cheese
    public Cheese createCheese(Cheese newCheese) {
        // Check if the provided cheese is valid
        if (!isValidCheese(newCheese)) {
            throw new IllegalArgumentException("Cheese must have a name, a price per kilo, and a color.");
        }
        // Check if the maximum number of cheeses has been reached
        if (cheeses.size() >= 5) {
            throw new RuntimeException("Maximum number of cheeses reached");
        }
        // Set a default image URL if none is provided
        if (newCheese.getImageUrl() == null || newCheese.getImageUrl().isEmpty()) {
            newCheese.setImageUrl("https://jackscheese.com/wp-content/uploads/Placeholder-Cheese.jpg.webp"); // Provide

        }
        // Add the new cheese to the list
        cheeses.add(newCheese);
        return newCheese;
    }

    // Method to update an existing cheese
    public Cheese updateCheese(String id, Cheese updatedCheese) {
        // Check if the provided cheese is valid
        if (!isValidCheese(updatedCheese)) {
            throw new IllegalArgumentException("Cheese must have a name, a price per kilo, and a color.");
        }
        // Iterate through cheeses to find the one with the matching ID
        for (int i = 0; i < cheeses.size(); i++) {
            Cheese cheese = cheeses.get(i);
            if (cheese.getId().equals(id)) {
                // Set a default image URL if none is provided
                if (updatedCheese.getImageUrl() == null || updatedCheese.getImageUrl().isEmpty()) {
                    updatedCheese.setImageUrl("https://jackscheese.com/wp-content/uploads/Placeholder-Cheese.jpg.webp"); // Provide

                }
                // Update the cheese in the list
                cheeses.set(i, updatedCheese);
                return updatedCheese;
            }
        }
        return null;
    }

    // Method to delete a cheese by its ID
    public boolean deleteCheese(String id) {
        return cheeses.removeIf(cheese -> cheese.getId().equals(id));
    }

    // Method to validate a cheese entity
    private boolean isValidCheese(Cheese cheese) {
        return cheese.getName() != null && !cheese.getName().isEmpty() &&
                cheese.getPricePerKilo() > 0 &&
                cheese.getColor() != null && !cheese.getColor().isEmpty();
    }
}