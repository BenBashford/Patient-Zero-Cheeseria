package test.java;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import main.java.CheeseService;
import main.java.CheeseriaApplication;
import main.java.Cheese;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CheeseriaApplication.class) // Specify the main application class
@AutoConfigureMockMvc
public class CheeseriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheeseService cheeseService;

    @Test
    public void testGetCheeseById_ExistingCheese() throws Exception {
        String cheeseId = "1";
        Cheese mockCheese = new Cheese(cheeseId, "Cheddar", 10.0, "Yellow", "example.com/cheddar.jpg");

        // Mock service behavior
        when(cheeseService.getCheeseById(cheeseId)).thenReturn(mockCheese);

        // Perform request and verify response
        mockMvc.perform(get("/cheeses/{cheeseId}", cheeseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cheeseId))
                .andExpect(jsonPath("$.name").value("Cheddar"))
                .andExpect(jsonPath("$.pricePerKilo").value(10.0))
                .andExpect(jsonPath("$.color").value("Yellow"))
                .andExpect(jsonPath("$.imageUrl").value("example.com/cheddar.jpg"));

        // Verify that the service method was called
        verify(cheeseService, times(1)).getCheeseById(cheeseId);
    }

    @Test
    public void testGetAllCheeses() throws Exception {

        List<Cheese> mockCheeses = new ArrayList<>();
        mockCheeses.add(new Cheese("1", "Cheddar", 10.0, "Yellow", "example.com/cheddar.jpg"));
        mockCheeses.add(new Cheese("2", "Gouda", 12.0, "White", "example.com/gouda.jpg"));
        when(cheeseService.getAllCheeses()).thenReturn(mockCheeses);

        mockMvc.perform(get("/cheeses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Cheddar"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Gouda"));

        verify(cheeseService, times(1)).getAllCheeses();
    }

    @Test
    public void testCreateCheese() throws Exception {
        Cheese newCheese = new Cheese("1", "Mozzarella", 15.0, "White", "example.com/mozzarella.jpg");
        when(cheeseService.createCheese(any(Cheese.class))).thenReturn(newCheese);

        mockMvc.perform(post("/cheeses")
                .contentType("application/json")
                .content(
                        "{\"name\": \"Mozzarella\", \"pricePerKilo\": 15.0, \"color\": \"White\", \"imageUrl\": \"example.com/mozzarella.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Mozzarella"))
                .andExpect(jsonPath("$.pricePerKilo").value(15.0))
                .andExpect(jsonPath("$.color").value("White"))
                .andExpect(jsonPath("$.imageUrl").value("example.com/mozzarella.jpg"));

        verify(cheeseService, times(1)).createCheese(any(Cheese.class));
    }

    @Test
    public void testUpdateCheese() throws Exception {
        String cheeseId = "1";
        Cheese updatedCheese = new Cheese(cheeseId, "Swiss", 18.0, "Yellow", "example.com/swiss.jpg");
        when(cheeseService.updateCheese(eq(cheeseId), any(Cheese.class))).thenReturn(updatedCheese);

        mockMvc.perform(put("/cheeses/{cheeseId}", cheeseId)
                .contentType("application/json")
                .content(
                        "{\"name\": \"Swiss\", \"pricePerKilo\": 18.0, \"color\": \"Yellow\", \"imageUrl\": \"example.com/swiss.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cheeseId))
                .andExpect(jsonPath("$.name").value("Swiss"))
                .andExpect(jsonPath("$.pricePerKilo").value(18.0))
                .andExpect(jsonPath("$.color").value("Yellow"))
                .andExpect(jsonPath("$.imageUrl").value("example.com/swiss.jpg"));

        verify(cheeseService, times(1)).updateCheese(eq(cheeseId), any(Cheese.class));
    }

    @Test
    public void testDeleteCheese() throws Exception {
        String cheeseId = "1";
        when(cheeseService.deleteCheese(cheeseId)).thenReturn(true);

        mockMvc.perform(delete("/cheeses/{cheeseId}", cheeseId))
                .andExpect(status().isOk());

        verify(cheeseService, times(1)).deleteCheese(cheeseId);
    }

}