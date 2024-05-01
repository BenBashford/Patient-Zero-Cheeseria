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

    @PostMapping("/cheeses")
    public ResponseEntity<Cheese> createCheese(@RequestBody Cheese newCheese) {
        Cheese createdCheese = cheeseService.createCheese(newCheese);
        return ResponseEntity.ok(createdCheese);
    }

    @PutMapping("/cheeses/{cheeseId}")
    public ResponseEntity<Cheese> updateCheese(@PathVariable String cheeseId, @RequestBody Cheese updatedCheese) {
        Cheese cheese = cheeseService.updateCheese(cheeseId, updatedCheese);
        if (cheese != null) {
            return ResponseEntity.ok(cheese);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cheeses/{cheeseId}")
    public ResponseEntity<?> deleteCheese(@PathVariable String cheeseId) {
        boolean deleted = cheeseService.deleteCheese(cheeseId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}