package main.java.Cheeseria;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheeseService {
    private List<Cheese> cheeses = new ArrayList<>();

    public CheeseService() {
        // Initialize cheeses
        cheeses.add(new Cheese("1", "Cheddar", 8.99, "Yellow"));
        cheeses.add(new Cheese("2", "Gouda", 10.99, "Yellow"));
        cheeses.add(new Cheese("3", "Brie", 12.99, "White"));
        cheeses.add(new Cheese("4", "Blue Cheese", 9.99, "Blue"));
        cheeses.add(new Cheese("5", "Feta", 7.99, "White"));
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
}