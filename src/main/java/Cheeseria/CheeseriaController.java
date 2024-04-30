package main.java.Cheeseria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CheeseriaController {

    @Autowired
    private CheeseService cheeseService;

    @GetMapping("/cheeses")
    public List<Cheese> getAllCheeses() {
        return cheeseService.getAllCheeses();
    }

    @GetMapping("/cheeses/{cheeseId}")
    public ResponseEntity<Cheese> getCheeseById(@PathVariable String cheeseId) {
        Cheese cheese = cheeseService.getCheeseById(cheeseId);
        if (cheese != null) {
            return ResponseEntity.ok(cheese);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}