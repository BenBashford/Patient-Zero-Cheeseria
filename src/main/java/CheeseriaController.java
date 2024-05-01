package main.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CheeseriaController {

    @Autowired
    private CheeseService cheeseService;

    @GetMapping("/cheeses")
    @Operation(summary = "Get all cheeses", description = "Get a list of all cheeses")
    public List<Cheese> getAllCheeses() {
        return cheeseService.getAllCheeses();
    }

    @GetMapping("/cheeses/{cheeseId}")
    @Operation(summary = "Get cheese by ID", description = "Get cheese information by its ID")
    @ApiResponse(responseCode = "200", description = "Found the cheese", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cheese.class)) })
    @ApiResponse(responseCode = "404", description = "Cheese not found")
    public ResponseEntity<Cheese> getCheeseById(
            @Parameter(description = "ID of the cheese to be obtained. Cannot be empty.", required = true) @PathVariable String cheeseId) {
        Cheese cheese = cheeseService.getCheeseById(cheeseId);
        if (cheese != null) {
            return ResponseEntity.ok(cheese);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cheeses")
    @Operation(summary = "Create a new cheese", description = "Create a new cheese with provided details")
    @ApiResponse(responseCode = "200", description = "The created cheese", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cheese.class)) })
    public ResponseEntity<Cheese> createCheese(
            @Parameter(description = "Cheese object to be created", required = true) @RequestBody Cheese newCheese) {
        Cheese createdCheese = cheeseService.createCheese(newCheese);
        return ResponseEntity.ok(createdCheese);
    }

    @PutMapping("/cheeses/{cheeseId}")
    @Operation(summary = "Update an existing cheese", description = "Update an existing cheese with provided details")
    @ApiResponse(responseCode = "200", description = "The updated cheese", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cheese.class)) })
    @ApiResponse(responseCode = "404", description = "Cheese not found")
    public ResponseEntity<Cheese> updateCheese(
            @Parameter(description = "ID of the cheese to be updated. Cannot be empty.", required = true) @PathVariable String cheeseId,
            @Parameter(description = "Updated cheese object", required = true) @RequestBody Cheese updatedCheese) {
        Cheese cheese = cheeseService.updateCheese(cheeseId, updatedCheese);
        if (cheese != null) {
            return ResponseEntity.ok(cheese);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cheeses/{cheeseId}")
    @Operation(summary = "Delete a cheese", description = "Delete a cheese by its ID")
    @ApiResponse(responseCode = "200", description = "Cheese deleted successfully")
    @ApiResponse(responseCode = "404", description = "Cheese not found")
    public ResponseEntity<?> deleteCheese(
            @Parameter(description = "ID of the cheese to be deleted. Cannot be empty.", required = true) @PathVariable String cheeseId) {
        boolean deleted = cheeseService.deleteCheese(cheeseId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}