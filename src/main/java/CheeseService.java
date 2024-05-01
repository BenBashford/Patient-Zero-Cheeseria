package main.java;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheeseService {
    private List<Cheese> cheeses = new ArrayList<>();

    public CheeseService() {

    }

    public List<Cheese> getAllCheeses() {
        return cheeses;
    }

    public Cheese getCheeseById(String id) {
        for (Cheese cheese : cheeses) {
            if (cheese.getId().equals(id)) {
                return cheese;
            }
        }
        return null;
    }

    public Cheese createCheese(Cheese newCheese) {
        if (!isValidCheese(newCheese)) {
            throw new IllegalArgumentException("Cheese must have a name, a price per kilo, and a color.");
        }
        if (cheeses.size() >= 5) {
            throw new RuntimeException("Maximum number of cheeses reached");
        }
        if (newCheese.getImageUrl() == null || newCheese.getImageUrl().isEmpty()) {
            newCheese.setImageUrl("https://jackscheese.com/wp-content/uploads/Placeholder-Cheese.jpg.webp"); // Provide

        }
        cheeses.add(newCheese);
        return newCheese;
    }

    public Cheese updateCheese(String id, Cheese updatedCheese) {
        if (!isValidCheese(updatedCheese)) {
            throw new IllegalArgumentException("Cheese must have a name, a price per kilo, and a color.");
        }
        for (int i = 0; i < cheeses.size(); i++) {
            Cheese cheese = cheeses.get(i);
            if (cheese.getId().equals(id)) {
                if (updatedCheese.getImageUrl() == null || updatedCheese.getImageUrl().isEmpty()) {
                    updatedCheese.setImageUrl("https://jackscheese.com/wp-content/uploads/Placeholder-Cheese.jpg.webp"); // Provide

                }
                cheeses.set(i, updatedCheese);
                return updatedCheese;
            }
        }
        return null;
    }

    public boolean deleteCheese(String id) {
        return cheeses.removeIf(cheese -> cheese.getId().equals(id));
    }

    private boolean isValidCheese(Cheese cheese) {
        return cheese.getName() != null && !cheese.getName().isEmpty() &&
                cheese.getPricePerKilo() > 0 &&
                cheese.getColor() != null && !cheese.getColor().isEmpty();
    }
}