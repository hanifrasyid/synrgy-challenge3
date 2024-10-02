package appModelTest;

import appModel.FoodItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

    @Test
    @DisplayName("test constructor and getters")
    public void testPositiveCase() {
        FoodItem foodItem = new FoodItem("Nasi Goreng", 15000);

        assertEquals("Nasi Goreng", foodItem.getName());
        assertEquals(15000, foodItem.getPrice());
    }

    @Test
    @DisplayName("test negative price")
    public void testNegativeCase() {
        assertThrows(IllegalArgumentException.class, () -> new FoodItem("Mie Goreng", -13000));
    }
}
